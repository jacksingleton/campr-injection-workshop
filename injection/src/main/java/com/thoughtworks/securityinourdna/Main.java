package com.thoughtworks.securityinourdna;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final UserRepo userRepo = initializeUserRepo();

    public static void main(final String[] args) throws Exception {
        while (true) {
            System.out.print("Hi what is your first name? ");

            final String firstName = new Scanner(System.in).nextLine();

            final String lastName = userRepo.findLastName(firstName);

            if (lastName != null) {
                System.out.println("Hi, " + firstName + " " + lastName);
            } else {
                System.out.println("Sorry, you're not in our database");
            }
        }
    }

    private static UserRepo initializeUserRepo() {
        try {
            final ConnectionFactory connectionFactory = new ConnectionFactory();
            final UserRepo userRepo = new UserRepo(connectionFactory.createInMemoryDatabase());

            userRepo.addNames(new HashMap<String, String>() {{
                put("Alice", "Brown");
                put("Bob", "Smith");
                put("Eve", "Johnson");
                put("Mallory", "Jones");
                put("Dan", "Williams");
            }});

            return userRepo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
