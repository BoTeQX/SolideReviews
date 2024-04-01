package org.solidereviews.interfaces;

import org.solidereviews.utils.Colors;

import java.util.Scanner;

public interface Menu {
    Scanner scanner = new Scanner(System.in);
    default void displayLogo() {
        System.out.println("     ▀▄   ▄▀                                  ▀▄   ▄▀     ");
        System.out.println("    ▄█▀███▀█▄    ─── Solide  ──              ▄█▀███▀█▄    ");
        System.out.println("   █▀███████▀█            ── Reviews ────   █▀███████▀█   ");
        System.out.println("   ▀ ▀▄▄ ▄▄▀ ▀                              ▀ ▀▄▄ ▄▄▀ ▀   ");
        System.out.println();
    }

    String getTitle();

    String[] getMenuItems();

    default void initiateMenu() {
        clearScreen();
        displayMenu(getTitle(), getMenuItems());
    }

    default void displayMenu(String title, String[] menuItems) {
        int choice;
        do {
            displayLogo();
            System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT +  title + " " + Colors.RESET);
            System.out.println("│");

            if(title.equals("MAIN MENU")) {
                System.out.println("├ <" + Colors.BLUE_BOLD + "1" + Colors.RESET + "> " + Colors.YELLOW_BOLD + "SALE" + Colors.RESET);
                for (int i = 0; i < menuItems.length; i++) {
                    System.out.println("├ <" + Colors.BLUE_BOLD + (i + 2) + Colors.RESET + "> " + menuItems[i]);
                }
            }
            else {
                for (int i = 0; i < menuItems.length; i++) {
                    System.out.println("├ <" + Colors.BLUE_BOLD + (i + 1) + Colors.RESET + "> " + menuItems[i]);
                }
            }

            System.out.println("│");
            if(!title.equals("MAIN MENU")) {
                System.out.println("├ <" + Colors.BLUE_BOLD + "9" + Colors.RESET + "> " + Colors.BLUE_BOLD + "Back" + Colors.RESET);
            }
            System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + "> " + Colors.RED + "Exit" + Colors.RESET);
            System.out.println();

            // Get and process user choice
            choice = getUserChoice();
            processUserChoice(choice);
        } while (choice != 0);
    }
    default int getUserChoice() {
        System.out.print(Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        if (choice < 0 || choice > 9) {
            System.out.println("Invalid input. Please enter a valid option.");
            return getUserChoice();
        }
        return choice;
    }

    void processUserChoice(int choice);

    void backToPreviousMenu();
    default void clearScreen() {
        System.out.print("\033\143");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    default void closeProgram() {
        System.out.println("Exiting the program. Goodbye!");
        System.exit(0);
    }

}
