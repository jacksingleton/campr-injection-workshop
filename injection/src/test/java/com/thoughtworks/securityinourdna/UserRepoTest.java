package com.thoughtworks.securityinourdna;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserRepoTest {


    @Test
    public void add_names_should_insert_names_into_the_database() throws Exception {
        // Given
        Connection conn = createInMemoryDatabase();

        Map<String, String> firstLastNames = new HashMap<String, String>() {{
            put("Alice", "Injector");
            put("Bob", "Injector");
        }};

        // When
        new UserRepo(conn).addNames(firstLastNames);

        // Then
        assertEquals(getUserCount(conn), 2);
    }


    @Test
    public void find_last_name_should_return_null_when_first_name_does_not_exist() throws Exception {
        // Given
        Connection conn = createInMemoryDatabase();

        // When
        String lastName = new UserRepo(conn).findLastName("i do not exist");

        // Then
        assertNull(lastName);
    }

    @Test
    public void find_last_name_should_return_the_last_name_of_a_user_in_the_database() throws Exception {
        // Given
        Connection conn = createInMemoryDatabase();
        UserRepo userRepo = new UserRepo(conn);

        userRepo.addNames(new HashMap<String, String>() {{
            put("Alice", "Injector");
        }});

        // When
        String lastName = userRepo.findLastName("Alice");

        // Then
        assertEquals(lastName, "Injector");
    }

    private Connection createInMemoryDatabase() throws SQLException {
        try {
            DriverManager.getConnection("jdbc:derby:memory:" + "injection" + ";drop=true");
        } catch (Exception e) {
            // Database doesn't exist in memory yet, no worries
        }

        Connection conn = DriverManager.getConnection("jdbc:derby:memory:" + "injection" + ";create=true");
        conn.createStatement().execute("create table users (first_name varchar(80), last_name varchar(80))");
        return conn;
    }

    private int getUserCount(Connection conn) throws Exception {
        final ResultSet resultSet = conn.createStatement().executeQuery("select count(*) as user_count from users");

        resultSet.next();

        return resultSet.getInt("user_count");
    }
}
