package com.thoughtworks.securityinourdna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String databaseDescriptor = "jdbc:postgresql://db:5432/" + "injection";
    private final String usersTableSql = "create table users (vendor_name varchar(80), password varchar(80))";

    public Connection createInMemoryDatabase() throws SQLException {
        // dropDatabaseIfItAlreadyExists();

        final Connection conn = DriverManager.getConnection(databaseDescriptor, "test","test");
        conn.createStatement().execute(usersTableSql);

        return conn;
    }

    private void dropDatabaseIfItAlreadyExists() {
        try {
            DriverManager.getConnection("jdbc:postgresql://db:5432/" + "injection" + ";drop=true", "test","test");
        } catch (SQLException e) {
            // Database doesn't exist in memory yet, no worries
        }
    }
}