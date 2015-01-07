package com.thoughtworks.securityinourdna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class UserRepo {

    private final Connection connection;

    public UserRepo(Connection connection) {
        this.connection = connection;
    }

    public String findLastName(final String name) throws Exception {
        final String query = "select * from users where first_name = '" + name + "'";

        final ResultSet resultSet = connection.createStatement().executeQuery(query);

        if (resultSet.next()) {
            return resultSet.getString("last_name");
        } else {
            return null;
        }

    }

    public void addNames(Map<String, String> firstLastNames) throws Exception {
        for (Map.Entry<String, String> firstLastName : firstLastNames.entrySet()) {
            final String query = "insert into users values (?, ?)";
            final PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstLastName.getKey());
            stmt.setString(2, firstLastName.getValue());
            stmt.execute();
        }
    }
}
