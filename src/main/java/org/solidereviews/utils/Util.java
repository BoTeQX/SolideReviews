package org.solidereviews.utils;

import java.util.Scanner;

public class Util {
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
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        clearScreen();
    }
}
