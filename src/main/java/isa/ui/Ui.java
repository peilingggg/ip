package isa.ui;

import java.util.Scanner;

/**
 * Handles console input and common messages shown to the user.
 */
public class Ui {
    private static final String DIVIDER =
            "____________________________________________________________";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints the greeting shown when Isa starts.
     */
    public void showGreeting() {
        System.out.println("Helloo! I'm Isa");
        System.out.println("How can I help you?");
        showDivider();
    }

    /**
     * Reads the next command from the console.
     *
     * @return Command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the divider between responses.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a command error.
     *
     * @param message Error message to show.
     */
    public void showError(String message) {
        System.out.println(" " + message);
    }

    /**
     * Prints the farewell message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope you have a nice day!");
    }

    /**
     * Closes the console scanner.
     */
    public void close() {
        scanner.close();
    }
}
