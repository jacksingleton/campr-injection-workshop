package com.thoughtworks.securityinourdna;

import java.util.Scanner;

public class Main {

    private static final UserRepo userRepo = initializeUserRepo();

    public static void main(final String[] args) throws Exception {
        System.out.print("Please Login.\n");

        System.out.print("Username: ");
        final String username = new Scanner(System.in).nextLine();
        System.out.print("Password: ");
        final String password = new Scanner(System.in).nextLine();

        if (userRepo.login(username, password)) {
            System.out.println("Welcome " + username + " " + userRepo.findLastName(username) + "!");
        } else {
            System.out.println("Sorry, please check your username and password combination.");
        }
    }

    private static UserRepo initializeUserRepo() {
        try {
            final ConnectionFactory connectionFactory = new ConnectionFactory();
            final UserRepo userRepo = new UserRepo(connectionFactory.createInMemoryDatabase());

            userRepo.addName("Alice", "Brown", "password");
            userRepo.addName("Bob", "Smith", "password");
            userRepo.addName("Eve", "Johnson", "password");
            userRepo.addName("Mallory", "Jones", "password");
            userRepo.addName("Dan", "Williams", "password");
            userRepo.addName("admin", "Almighty", "password");

            return userRepo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
