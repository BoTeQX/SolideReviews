package org.solidereviews.interfaces;

import org.solidereviews.utils.Colors;

import java.util.Scanner;

public interface Menu {
    Scanner scanner = new Scanner(System.in); // Scanner object to get user input

    // Method to display the logo
    default void displayLogo() {
        System.out.println("     ▀▄   ▄▀                                  ▀▄   ▄▀     ");
        System.out.println("    ▄█▀███▀█▄    ─── Solide  ──              ▄█▀███▀█▄    ");
        System.out.println("   █▀███████▀█            ── Reviews ────   █▀███████▀█   ");
        System.out.println("   ▀ ▀▄▄ ▄▄▀ ▀                              ▀ ▀▄▄ ▄▄▀ ▀   ");
        System.out.println();
    }


    String getTitle(); // Get the title of the current menu

    String[] getMenuItems(); // Get the menu items of the current menu

    // Method to initiate a menu
    default void initiateMenu() {
        clearScreen(); // Clear the screen
        displayMenu(getTitle(), getMenuItems()); // Display the menu using the title and menu items of the current menu
    }

    // Method to display a menu using a title and menu items
    default void displayMenu(String title, String[] menuItems) {
        int choice; // Variable containing a users choice in a menu

        // Do while loop to display the menu until the user exits the program
        do {
            clearScreen(); // Clear the screen
            displayLogo(); // Display the logo
            System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT +  title + " " + Colors.RESET); // Display the title of the current menu
            System.out.println("│");

               // Display the menu items of the current menu
                for (int i = 0; i < menuItems.length; i++) {
                    System.out.println("├ <" + Colors.BLUE_BOLD + (i + 1) + Colors.RESET + "> " + menuItems[i]); // Loop through and display the menu items of the current menu
                }

            System.out.println("│");

             // if the current menu is not "MAIN MENU", display the back option, since the main menu does not have a back option
            if(!title.equals("MAIN MENU")) {
                System.out.println("├ <" + Colors.BLUE_BOLD + "9" + Colors.RESET + "> " + Colors.BLUE_BOLD + "Back" + Colors.RESET);
            }
            System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + "> " + Colors.RED + "Exit" + Colors.RESET); // Display the exit option to exit the program
            System.out.println();

            // Get and process user choice
            choice = getUserChoice(); // Get the user's choice
            processUserChoice(choice); // Process the user's choice to determine the next action
        } while (choice != 0); // Continue displaying the menu until the user exits the program
    }

    // Method to get/ask for the user's choice
    default int getUserChoice() {
        System.out.print(Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);

        // Check if the user entered a number
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number."); // If the user did not enter a number, display an error message
            scanner.next(); // Clear the scanner
        }
        int choice = scanner.nextInt(); // Get the user's input/choice

        // Check if the user entered a valid  menu option (0-9 since a menu never has 10 or more options and 0 is always exit in all menus)
        if (choice < 0 || choice > 9) {
            System.out.println("Invalid input. Please enter a valid option."); // If the user entered an invalid option, display an error message
            return getUserChoice(); // Ask for the user to choose a menu option again
        }
        return choice; // Return the user's choice
    }

    void processUserChoice(int choice); // Process the user's choice to determine the next action in the menu

    // Method to clear the screen in the console (there are multiple ways to clear the screen in the console, this uses multiple of them to ensure compatibility with different operating systems and consoles)
    default void clearScreen() {
        System.out.print("\033\143"); // Clear the screen
        System.out.print("\033[H\033[2J"); // Clear the screen
        System.out.flush(); // Clear the screen
    }

    // Method to close the program
    default void closeProgram() {
        System.out.println("Exiting the program. Goodbye!"); // Display a goodbye message
        System.exit(0); // Exit the program with a status code of 0 (successful exit)
    }

    // Method to press enter to continue
    default void pressToContinue() {
        System.out.println(Colors.BLUE_BOLD + "\nPress Enter to continue..." + Colors.RESET); // Display a message to press enter to continue
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for the user to press enter
        clearScreen(); // Clear the screen
    }
}
