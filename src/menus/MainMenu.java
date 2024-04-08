package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.utils.Colors;
import org.solidereviews.utils.GlobalFunctions;
import java.util.HashMap;
import java.util.Map;

public class MainMenu implements Menu {
    String title = "MAIN MENU";
    String[] menuItems = {Colors.YELLOW_BOLD + "SALE" + Colors.RESET , "Games", "Admin" };
    private final Map<String, String> adminCredentials;

    public MainMenu() {
        this.adminCredentials = new HashMap<>();
        adminCredentials.put("admin", "admin"); 
    }

    @Override
    public String getTitle() {return title;} // Return the title of the menu

    @Override
    public String[] getMenuItems() {return menuItems;} // Return the menu items


    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> System.out.println("You selected Option 1.");
            case 2 -> new GamesMenu().initiateMenu();
            case 3 -> adminLogin();
            case 0 -> GlobalFunctions.closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private void adminLogin() {
        System.out.print("Enter username: ");
        String username = scanner.next(); // Get the username from the user
        System.out.print("Enter password: ");
        String password = scanner.next(); // Get the password from the user

        // Check if the entered credentials match admin credentials in the hashmap
        if (adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password)) {
            System.out.println("Admin login successful!");
            new AdminMenu().initiateMenu(); // If the credentials match, the AdminMenu gets initiated
        } else {
            try {
                System.out.println("Incorrect username or password. Please try again."); // If the credentials do not match, an error message gets displayed
                Thread.sleep(2000); // Wait for 2 seconds
                new MainMenu().initiateMenu(); // Re-initiate the MainMenu after 2 seconds
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
