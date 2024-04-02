package org.solidereviews.submenus.admin;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.AdminMenu;

public class SurveysSubmenu implements Menu {

    String title = "ADMIN MENU > Surveys";
    String[] menuItems = {"Create Survey", "Update survey", "Delete survey", "Show survey result(s)"};

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
            case 2 -> System.out.println("You selected Option 2.");
            case 3 -> System.out.println("You selected Option 3.");
            case 4 -> System.out.println("You selected Option 4.");
            case 9 -> new AdminMenu().initiateMenu();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

}
