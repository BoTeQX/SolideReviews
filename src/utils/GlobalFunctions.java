package org.solidereviews.utils;

import java.util.Scanner;

public class GlobalFunctions {
    static Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033\143");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void closeProgram() {
        System.out.println("Exiting the program. Goodbye!");
        System.exit(0);
    }

    public static void pressToContinue() {
        System.out.println(Colors.BLUE_BOLD + "\nPress Enter to continue..." + Colors.RESET);
        scanner.nextLine();
        clearScreen();
    }

    public static boolean createConfirmMenu(String titleMenu) {
        System.out.println("├─> " + Colors.CYAN_BOLD_BRIGHT + titleMenu + Colors.RESET);
        System.out.println("│");
        System.out.println("├ <" + Colors.BLUE_BOLD + "1" + Colors.RESET + "> Yes");
        System.out.println("├ <" + Colors.BLUE_BOLD + "2" + Colors.RESET + "> No");
        System.out.println("│");
        System.out.print("╰ " + Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);

        if (!scanner.hasNextInt()) {
            scanner.next();
            return createConfirmMenu(titleMenu);
        }

        int choice = scanner.nextInt();
        if (choice < 0 || choice > 2)
            return createConfirmMenu(titleMenu);

        return choice == 1;
    }

    // Error messages
    public static final String ERROR_INVALID_CHOICE = "Invalid choice. Please enter a valid option.";
    public static final String ERROR_INVALID_NUMBER = "Invalid input. Please enter a valid number.";
}