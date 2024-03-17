package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.utils.Colors;

import java.util.Scanner;

public class MainMenu implements Menu {
    private final Scanner scanner;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayLogo() {
        System.out.println("     ▀▄   ▄▀                                  ▀▄   ▄▀     ");
        System.out.println("    ▄█▀███▀█▄    ─── Solide™ ──              ▄█▀███▀█▄    ");
        System.out.println("   █▀███████▀█            ── Reviews ────   █▀███████▀█   ");
        System.out.println("   ▀ ▀▄▄ ▄▄▀ ▀                              ▀ ▀▄▄ ▄▄▀ ▀   ");
        System.out.println();
    }

    @Override
    public void displayMenu() {
        int choice;
        do {
            displayLogo();
            System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "MAIN MENU " + Colors.RESET);
            System.out.println("│");
            System.out.println("├ <"+ Colors.BLUE_BOLD + "1" + Colors.RESET + "> Games");
            System.out.println("├ <"+ Colors.BLUE_BOLD + "2" + Colors.RESET + "> Option 2");
            System.out.println("├ <"+ Colors.BLUE_BOLD + "3" + Colors.RESET + "> Option 3");
            System.out.println("├ <"+ Colors.BLUE_BOLD + "4" + Colors.RESET + "> Admin");
            System.out.println("│");
            System.out.println("╰ <"+ Colors.BLUE_BOLD + "0" + Colors.RESET + "> " + Colors.RED + "Exit" + Colors.RESET);
            System.out.println();

            // Get user choice
            choice = getUserChoice();
            processUserChoice(choice);
        } while (choice != 0);
    }

    @Override
    public int getUserChoice() {
        System.out.print(Colors.PURPLE +"Enter your choice: " + Colors.RESET);
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
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    @Override
    public void openNewMenu(Menu menu) {
        clearScreen();
        menu.displayMenu();
    }

    @Override
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    @Override
    public void closeProgram() {
        System.out.println("Exiting the program. Goodbye!");
        scanner.close();
    }
}
