package com.gumtree.challenge;

import com.gumtree.challenge.dto.AddressBook;
import com.gumtree.challenge.dto.Person;

import java.io.IOException;

/**
 * This class is the main launcher which answers the challenge questions
 */
public class Launcher {

    public static void main(String[] args) throws IOException {
        showWelcomeMessage();

        AddressBook addressBook = new AddressBook();

        System.out.println("Q1. How many males are in the address book?");
        System.out.printf("A1. There are %d males in total%n\n",
                addressBook.findCountByGender(Person.Gender.MALE));

        System.out.println("Q2. Who is the oldest person in the address book?");
        System.out.printf("A2. The eldest person is %s%n\n",
                addressBook.findEldestPerson().getFullName());

        System.out.println("Q3. How many days older is Bill than Paul?");
        System.out.printf("A3. Bill is older than Paul by %d days%n\n",
                addressBook.findAgeDifferenceInDays("Bill", "Paul"));

    }

    /**
     * Display a welcome message when the program starts executing
     */
    private static void showWelcomeMessage() {
        System.out.println("\n" +
                "____ _  _ _  _ ___ ____ ____ ____    ____ _  _ ____ _    _    ____ _  _ ____ ____ \n" +
                "| __ |  | |\\/|  |  |__/ |___ |___    |    |__| |__| |    |    |___ |\\ | | __ |___ \n" +
                "|__] |__| |  |  |  |  \\ |___ |___    |___ |  | |  | |___ |___ |___ | \\| |__] |___ \n");
        System.out.println(
                "                                            /_     / `/._ _/_  / `_  //_  ._ \n" +
                "                                           /_//_/ /_,/// //   /_,/_|///_'//_|\n" +
                "                                              _/                       |/    \n"
        );
    }
}
