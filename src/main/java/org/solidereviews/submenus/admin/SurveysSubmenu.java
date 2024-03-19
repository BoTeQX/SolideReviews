package org.solidereviews.submenus.admin;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.AdminMenu;

public class SurveysSubmenu implements Menu {

    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> System.out.println("You selected Option 1.");
            case 2 -> System.out.println("You selected Option 2.");
            case 3 -> System.out.println("You selected Option 3.");
            case 4 -> System.out.println("You selected Option 4.");
            case 9 -> backToPreviousMenu();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    @Override
    public void backToPreviousMenu() {
       Menu menu = new AdminMenu();
         String title = "ADMIN MENU";
            String[] menuItems = {"Manage game catalog", "Surveys"};
            switchToMenu(menu, title, menuItems);
    }
}
