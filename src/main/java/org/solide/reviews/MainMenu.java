package org.solide.reviews;

import java.util.Scanner;

public class MainMenu implements Menu {
    private final Scanner scanner;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayLogo() {
        System.out.println("     ▀▄   ▄▀                                  ▀▄   ▄▀     ");
        System.out.println("    ▄█▀███▀█▄    ──── Solide ──              ▄█▀███▀█▄    ");
        System.out.println("   █▀███████▀█            ── Reviews ────   █▀███████▀█   ");
        System.out.println("   ▀ ▀▄▄ ▄▄▀ ▀                              ▀ ▀▄▄ ▄▄▀ ▀   ");
        System.out.println();
    }

    @Override
    public void displayMenu() {
        int choice;
        do {
            displayLogo();
            System.out.println("Main Menu:");
            System.out.println("[1] Option 1");
            System.out.println("[2] Option 2");
            System.out.println("[3] Option 3");
            System.out.println();
            System.out.println("[0] Exit");

            System.out.print("Enter your choice: ");
            choice = getUserChoice();

            processUserChoice(choice);
        } while (choice != 0);

        scanner.close();
    }

    @Override
    public int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> System.out.println("You selected Option 1.");
            case 2 -> System.out.println("You selected Option 2.");
            case 3 -> System.out.println("You selected Option 3.");
            case 0 -> System.out.println("Exiting the program. Goodbye!");
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }

        System.out.print("Press Enter to continue...");
        scanner.nextLine(); // Consume newline character
        scanner.nextLine(); // Wait for Enter key press
    }


    @Override
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
    }
}
