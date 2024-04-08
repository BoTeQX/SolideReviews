package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.admin.ManageGameCatalogSubmenu;
import org.solidereviews.submenus.admin.SurveysSubmenu;

import org.solidereviews.utils.GlobalFunctions;

public class AdminMenu implements Menu {

    String title = "ADMIN MENU";
    String[] menuItems = { "Manage game catalog", "Surveys" };

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
            case 1 -> new ManageGameCatalogSubmenu().initiateMenu();
            case 2 -> new SurveysSubmenu().initiateMenu();
            case 9 -> new MainMenu().initiateMenu();
            case 0 -> GlobalFunctions.closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

}
