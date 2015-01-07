package com.thoughtworks.securityinourdna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final UserRepo userRepo = initializeUserRepo();

    public static void main(String[] args) throws Exception {
        System.out.print("Hi what is your first name? ");

        final String firstName = new Scanner(System.in).nextLine();

        final String lastName = userRepo.findLastName(firstName);

        if (lastName != null) {
            System.out.println("Hi, " + firstName + " " + lastName);
        } else {
            System.out.println("Sorry, you're not in our database");
        }
    }

    private static UserRepo initializeUserRepo() {
        try {
            final Connection conn = DriverManager.getConnection("jdbc:derby:memory:" + "injection" + ";create=true");
            conn.createStatement().execute("create table users (first_name varchar(80), last_name varchar(80))");
            UserRepo userRepo = new UserRepo(conn);

            userRepo.addNames(new HashMap<String, String>() {{
                put("Alice", "Injector1");
                put("Bob", "Injector2");
            }});

            return userRepo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
