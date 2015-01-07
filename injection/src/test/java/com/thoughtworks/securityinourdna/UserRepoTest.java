package com.thoughtworks.securityinourdna;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UserRepoTest {


    @Test
    public void add_names_should_insert_names_into_the_database() throws Exception {
        // Given
        String connectionURL = "jdbc:derby:memory:" + "injection" + ";create=true";
        Connection conn = DriverManager.getConnection(connectionURL);
        conn.createStatement().execute("create table users (first_name varchar(80), last_name varchar(80))");

        Map<String, String> firstLastNames = new HashMap<String, String>() {{
            put("Alice", "Injector");
            put("Bob", "Injector");
        }};


        // When
        new UserRepo(conn).addNames(firstLastNames);

        // Then
        assertEquals(getUserCount(conn), 2);
    }

//    @Test
//    public void find_last_name_should_return_null_when_first_name_does_not_exist() throws Exception {
//
//    }

    private int getUserCount(Connection conn) throws Exception {
        final ResultSet resultSet = conn.createStatement().executeQuery("select count(*) as user_count from users");

        resultSet.next();

        return resultSet.getInt("user_count");
    }
}
