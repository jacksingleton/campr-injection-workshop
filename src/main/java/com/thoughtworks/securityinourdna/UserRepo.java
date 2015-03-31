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

    public String findLastName(final String username) throws Exception {
        final String query = "select * from users where first_name = '" + username + "'";

        final ResultSet resultSet = connection.createStatement().executeQuery(query);

        if (resultSet.next()) {
            return resultSet.getString(2);
        } else {
            return null;
        }

    }

    public void addName(String firstname, String lastname, String password) throws Exception {
        final String query = "insert into users values (?, ?, ?)";
        final PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, firstname);
        stmt.setString(2, lastname);
        stmt.setString(3, password);
        stmt.execute();
    }

    public boolean login(String username, String password) throws SQLException {
        final String query = "select * from users where first_name = '" + username + "' and password = '" + password + "'";

        final ResultSet resultSet = connection.createStatement().executeQuery(query);

        return resultSet.next();
    }
}
