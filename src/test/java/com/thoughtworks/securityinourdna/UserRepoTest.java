package com.thoughtworks.securityinourdna;

import org.junit.After;
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

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_names_should_insert_names_into_the_database() throws Exception {
        // Given
        UserRepo repo = new UserRepo(conn);

        // When
        repo.addName("Alice", "password");
        repo.addName("Bob", "password");

        // Then
        assertEquals(getUserCount(conn), 2);
    }


    @Test
    public void login_should_work_for_existing_user() throws Exception {
        // Given
        UserRepo repo = new UserRepo(conn);
        repo.addName("Alice", "password");

        // When
        Boolean loggedIn = repo.login("Alice", "password").success;

        // Then
        assertTrue(loggedIn);
    }

    @Test
    public void login_should_return_vendor_name_for_existing_user() throws Exception {
        // Given
        UserRepo repo = new UserRepo(conn);
        repo.addName("Alice", "password");

        // When
        String vendorName = repo.login("Alice", "password").vendorName;

        // Then
        assertNotNull(vendorName);
    }

    @Test
    public void login_should_deny_non_existing_user() throws Exception {
        // Given
        UserRepo repo = new UserRepo(conn);
        repo.addName("Alice", "password");

        // When
        Boolean loggedIn = repo.login("nobody", "password").success;

        // Then
        assertFalse(loggedIn);
    }

    @Test
    public void login_should_not_be_vulnerable_to_obvious_sql_injection() throws Exception {
        // Given
        UserRepo userRepo = new UserRepo(conn);
        userRepo.addName("Alice", "password");

        // When
        Boolean loggedIn = userRepo.login("Alice", "' or 1=1 --comment").success;

        // Then
        assertFalse(loggedIn);
    }

    private int getUserCount(Connection conn) throws Exception {
        final String userCountQuery = "select count(*) as user_count from users";
        final ResultSet resultSet = conn.createStatement().executeQuery(userCountQuery);

        resultSet.next();

        return resultSet.getInt("user_count");
    }
}
