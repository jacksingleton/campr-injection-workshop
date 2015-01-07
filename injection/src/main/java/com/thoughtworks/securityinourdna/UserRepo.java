package com.thoughtworks.securityinourdna;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public String findLastName(final String name) {
        throw new RuntimeException("not yet implemented");
    }
}
