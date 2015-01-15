package com.thoughtworks.securityinourdna;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class UserRepoTest {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    @Test
    public void add_names_should_insert_names_into_the_database() throws Exception {
        // Given
        Connection conn = connectionFactory.createInMemoryDatabase();

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
        Connection conn = connectionFactory.createInMemoryDatabase();

        // When
        String lastName = new UserRepo(conn).findLastName("i do not exist");

        // Then
        assertNull(lastName);
    }

    @Test
    public void find_last_name_should_return_the_last_name_of_a_user_in_the_database() throws Exception {
        // Given
        Connection conn = connectionFactory.createInMemoryDatabase();
        UserRepo userRepo = new UserRepo(conn);

        userRepo.addNames(new HashMap<String, String>() {{
            put("Alice", "Injector");
        }});

        // When
        String lastName = userRepo.findLastName("Alice");

        // Then
        assertEquals(lastName, "Injector");
    }

    @Test
    public void find_last_name_should_not_be_vulnerable_to_obvious_sql_injection() throws Exception {
        // Given
        Connection conn = connectionFactory.createInMemoryDatabase();
        UserRepo userRepo = new UserRepo(conn);

        userRepo.addNames(new HashMap<String, String>() {{
            put("Alice", "Injector");
        }});

        // When
        String lastName = userRepo.findLastName("' or 1=1 --comment");

        // Then
        assertNull(lastName);
    }


    private int getUserCount(Connection conn) throws Exception {
        final String userCountQuery = "select count(*) as user_count from users";
        final ResultSet resultSet = conn.createStatement().executeQuery(userCountQuery);

        resultSet.next();

        return resultSet.getInt("user_count");
    }
}
