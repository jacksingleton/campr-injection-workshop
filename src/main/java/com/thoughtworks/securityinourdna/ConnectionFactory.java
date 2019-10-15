package com.thoughtworks.securityinourdna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class ConnectionFactory {

    private final String databaseDescriptor = "jdbc:mysql://db_server:3306/injection?autoReconnect=true&allowMultiQueries=true";
    private final String usersTableSql = "create table users (vendor_name varchar(80), password varchar(80))";

    public Connection createInMemoryDatabase() throws SQLException {
        int size =0;
        final Connection conn = DriverManager.getConnection(databaseDescriptor, "root","");
        boolean tableFound = false;
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet res = meta.getTables(null, null, "users", new String[] {"TABLE"});
        if (res != null) 
        {
            res.last();
            size = res.getRow();
        }
        System.out.println("****************");
        System.out.println(size);
        System.out.println("****************");
        if (size == 0){
            conn.createStatement().execute(usersTableSql);   
        }       
         

        return conn;
    }

}