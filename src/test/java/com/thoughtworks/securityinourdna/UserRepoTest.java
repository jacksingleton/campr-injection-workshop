package com.thoughtworks.securityinourdna;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class UserRepoTest {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private Connection conn;

    @Before
    public void setup() throws Exception {
        conn = connectionFactory.createInMemoryDatabase();
    }

    @Test
    public void add_names_should_insert_names_into_the_database() throws Exception {
        // Given
        UserRepo repo = new UserRepo(conn);

        // When
        repo.addName("Alice", "Injector", "password");
        repo.addName("Bob", "Injector", "password");

        // Then
        assertEquals(getUserCount(conn), 2);
    }


    @Test
    public void login_should_work_for_existing_user() throws Exception {
        // Given
        UserRepo repo = new UserRepo(conn);
        repo.addName("Alice", "Injector", "password");

        // When
        Boolean status = repo.login("Alice", "password");

        // Then
        assertTrue(status);
    }

    @Test
    public void login_should_deny_non_existing_user() throws Exception {
        // Given
        UserRepo repo = new UserRepo(conn);
        repo.addName("Alice", "Injector", "password");

        // When
        Boolean status = repo.login("nobody", "password");

        // Then
        assertFalse(status);
    }

    @Test
    public void find_last_name_should_return_the_last_name_of_a_user_in_the_database() throws Exception {
        // Given
        UserRepo userRepo = new UserRepo(conn);
        userRepo.addName("Alice", "Injector", "password");

        // When
        String lastName = userRepo.findLastName("Alice");

        // Then
        assertEquals(lastName, "Injector");
    }

    @Test
    public void find_last_name_should_not_be_vulnerable_to_obvious_sql_injection() throws Exception {
        // Given
        UserRepo userRepo = new UserRepo(conn);
        userRepo.addName("Alice", "Injector", "password");

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
