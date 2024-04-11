package menus;

import interfaces.Menu;
import utils.Colors;
import utils.GlobalFunctions;
import java.util.HashMap;
import java.util.Map;
import games.SaleController;

public class MainMenu implements Menu {
    String title = "MAIN MENU";
    String[] menuItems = {Colors.YELLOW_BOLD + "SALE" + Colors.RESET , "Games", "Admin" };
    private final Map<String, String> adminCredentials;

    public MainMenu() {
        this.adminCredentials = new HashMap<>();
        adminCredentials.put("admin", "admin"); 
    }

    @Override
    public String getTitle() {return title;}

    @Override
    public String[] getMenuItems() {return menuItems;}


    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> SaleController.showGamesOnSale();
            case 2 -> new GamesMenu().initiateMenu();
            case 3 -> adminLogin();
            case 0 -> GlobalFunctions.closeProgram();
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
            new AdminMenu().initiateMenu(); // If the credentials match, the AdminMenu gets initiated
        } else {
            try {
                System.out.println("Incorrect username or password. Please try again."); // If the credentials do not match, an error message gets displayed
                Thread.sleep(2000); // Wait for 2 seconds
                new MainMenu().initiateMenu();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
