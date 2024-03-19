package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;


import java.util.HashMap;
import java.util.Map;


public class MainMenu implements Menu {

    private final Map<String, String> adminCredentials;

    public MainMenu() {
        // Add admin credentials to a hashmap (username, password)
        this.adminCredentials = new HashMap<>();
        adminCredentials.put("admin", "admin");
    }

    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> System.out.println("You selected Option 1.");
            case 2 -> option2();
            case 3 -> System.out.println("You selected Option 3.");
            case 4 -> adminLogin();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    @Override
    public void backToPreviousMenu() {
        System.out.println("You are already in the main menu.");
    }


    private void option2() {
        Menu menu = new GamesMenu();
        String title = "GAMES MENU";
        String[] menuItems = {"Game catalog", "Review game"};
        switchToMenu(menu, title, menuItems);
    }

    private void adminLogin() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Check if the entered credentials match admin credentials in the hashmap
        if (adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password)) {
            System.out.println("Admin login successful!");
            Menu menu = new AdminMenu();
            String title = "ADMIN MENU";
            String[] menuItems = {"Manage game catalog", "Reviews", "Surveys"};
            switchToMenu(menu, title, menuItems); //switching to AdminMenu
        } else {
            System.out.println("Incorrect username or password. Please try again.");
        }
    }
}
