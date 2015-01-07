package com.thoughtworks.securityinourdna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class UserRepo {

    private final Connection connection;

    public UserRepo(Connection conn) {
        connection = conn;
    }

    public void addNames(Map<String, String> firstLastNames) throws Exception {
        for (Map.Entry<String, String> firstLastName : firstLastNames.entrySet()) {
            PreparedStatement stmt = connection.prepareStatement("insert into users values (?, ?)");
            stmt.setString(1, firstLastName.getKey());
            stmt.setString(2, firstLastName.getValue());
            stmt.execute();
        }
    }

    public String findLastName(final String name) throws Exception {
        final ResultSet resultSet = connection.createStatement().executeQuery("select * from users where first_name = '" + name + "'");

        if (resultSet.next()) {
            return resultSet.getString("last_name");
        } else {
            return null;
        }

    }
}
