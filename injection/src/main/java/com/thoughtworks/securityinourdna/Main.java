package com.thoughtworks.securityinourdna;

import java.util.Scanner;

public class Main {

    private static final UserRepo userRepo = new UserRepo(null);

    public static void main(String[] args) {
        System.out.print("Hi what is your first name? ");

        String firstName = new Scanner(System.in).nextLine();

        final String lastName = userRepo.findLastName(firstName);

        if (lastName != null) {
            System.out.println("Hi, " + firstName + " " + lastName);
        } else {
            System.out.println("Sorry, you're not in our database");
        }
    }
}
