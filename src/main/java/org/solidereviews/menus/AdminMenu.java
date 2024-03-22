package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.admin.ManageGameCatalogSubmenu;
import org.solidereviews.submenus.admin.SurveysSubmenu;

public class AdminMenu implements Menu {

    String title = "ADMIN MENU";
    String[] menuItems = {"Manage game catalog", "Surveys"};

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
            case 1 -> option1();
            case 2 -> option2();
            case 9 -> backToPreviousMenu();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private void option1() {
        Menu menu = new ManageGameCatalogSubmenu();
        menu.initiateMenu();
    }

    private void option2() {
        Menu menu = new SurveysSubmenu();
        menu.initiateMenu();
    }

    @Override
    public void backToPreviousMenu() {
        Menu menu = new MainMenu();
        String title = "MAIN MENU";
        String[] menuItems = {"Games", "Reviews", "Admin"};
        switchToMenu(menu, title, menuItems);
    }

}
