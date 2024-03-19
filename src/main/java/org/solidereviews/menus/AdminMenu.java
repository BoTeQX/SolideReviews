package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.admin.ManageGameCatalogSubmenu;
import org.solidereviews.submenus.admin.SurveysSubmenu;

public class AdminMenu implements Menu {

    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> option1();
            case 2 -> option2();
            case 9 -> backToPreviousMenu();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private void option1() {
        Menu menu = new ManageGameCatalogSubmenu();
        String title = "ADMIN MENU > Manage game catalog";
        String[] menuItems = {"Add game", "Remove game", "Update game", "Add sale", "View game catalog"};
        switchToMenu(menu, title, menuItems);
    }

    private void option2() {
        Menu menu = new SurveysSubmenu();
        String title = "ADMIN MENU > Surveys";
        String[] menuItems = {"Create Survey", "Update survey", "Delete survey", "Show survey result(s)"};
        switchToMenu(menu, title, menuItems);
    }

    @Override
    public void backToPreviousMenu() {
        Menu menu = new MainMenu();
        String title = "MAIN MENU";
        String[] menuItems = {"Games", "Reviews", "Admin"};
        switchToMenu(menu, title, menuItems);
    }

}
