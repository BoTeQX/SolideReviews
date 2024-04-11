package interfaces;

import utils.Colors;
import utils.GlobalFunctions;

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

    // Method to initiate a menu
    default void initiateMenu() {
         GlobalFunctions.clearScreen();
        displayMenu(getTitle(), getMenuItems()); // Display the menu using the title and menu items of the current menu
    }


    default void displayMenu(String title, String[] menuItems) {
        int choice;

        // Loops to display the menu until the user exits the program
        do {
             GlobalFunctions.clearScreen(); // Clear the screen
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

            choice = getUserChoice();
            processUserChoice(choice);
        } while (choice != 0); // Continue displaying the menu until the user exits the program (0 is the exit option in all menus)
    }

    default int getUserChoice() {
        System.out.print(Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number."); // If the user entered a non-integer value, display an error message
            scanner.next(); // Clear the scanner
        }
        int choice = scanner.nextInt();

        // Check if the user entered a valid  menu option (0-9 since a menu never has 10 or more options and 0 is always exit in all menus)
        if (choice < 0 || choice > 9) {
            System.out.println("Invalid input. Please enter a valid option.");
            return getUserChoice();
        }
        return choice;
    }
    void processUserChoice(int choice);
}
