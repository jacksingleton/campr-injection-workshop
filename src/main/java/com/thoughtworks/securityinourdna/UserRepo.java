package com.thoughtworks.securityinourdna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    private final Connection connection;

    public UserRepo(Connection connection) {
        this.connection = connection;
    }

    public void addName(String firstname, String password) throws Exception {
        final String query = "insert into users values (?, ?)";
        final PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, firstname);
        stmt.setString(2, password);
        stmt.execute();
    }

    public boolean login(String vendorName, String password) throws SQLException {
        final String query = "select * from users where vendor_name = '" + vendorName + "' and password = '" + password + "'";

        final ResultSet resultSet = connection.createStatement().executeQuery(query);

        return resultSet.next();
    }
}
