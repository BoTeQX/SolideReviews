package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;


import java.util.HashMap;
import java.util.Map;


public class MainMenu implements Menu {
    String title = "MAIN MENU";
    String[] menuItems = {"Games","Admin"};
    private final Map<String, String> adminCredentials;

    public MainMenu() {
        // Add admin credentials to a hashmap (username, password)
        this.adminCredentials = new HashMap<>();
        adminCredentials.put("admin", "admin");
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String[] getMenuItems() {
        return menuItems;
    }

    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> System.out.println("You selected Option 1.");
            case 2 -> new GamesMenu().initiateMenu();
            case 3 -> adminLogin();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private void adminLogin() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Check if the entered credentials match admin credentials in the hashmap
        if (adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password)) {
            System.out.println("Admin login successful!");
            new AdminMenu().initiateMenu();
        } else {
            try {
                System.out.println("Incorrect username or password. Please try again.");
                Thread.sleep(2000);
                new MainMenu().initiateMenu();
            } catch (InterruptedException e) {
               System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
