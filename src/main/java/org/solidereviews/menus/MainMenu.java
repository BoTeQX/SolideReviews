package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.utils.Colors;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu implements Menu {
    private final Scanner scanner;
    private final Map<String, String> adminCredentials;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
        this.adminCredentials = new HashMap<>();
        // Add admin credentials to the map (username, password)
        adminCredentials.put("admin", "admin");
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

            // Get and process user choice
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
            case 4 -> adminLogin();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    @Override
    public void switchToMenu(Menu menu) {
        clearScreen();
        menu.displayMenu();
    }

    @Override
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    @Override
    public void backToPreviousMenu() {
        System.out.println("You are already in the main menu.");
    }

    @Override
    public void closeProgram() {
        System.out.println("Exiting the program. Goodbye!");
        scanner.close();
    }

    private void adminLogin() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Check if the entered credentials match admin credentials
        if (adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password)) {
            System.out.println("Admin login successful!");
            switchToMenu(new AdminMenu()); //switching to AdminMenu
        } else {
            System.out.println("Incorrect username or password. Please try again.");
        }
    }
}
