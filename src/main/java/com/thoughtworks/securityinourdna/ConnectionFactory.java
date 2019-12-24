package com.thoughtworks.securityinourdna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String databaseDescriptor = "jdbc:h2:mem:injection";
    private final String usersTableSql = "create table users (vendor_name varchar(80), password varchar(80))";

    public Connection createInMemoryDatabase() throws SQLException {

        final Connection conn = DriverManager.getConnection(databaseDescriptor);
        conn.createStatement().execute(usersTableSql);

        return conn;
    }
}