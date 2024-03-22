package org.solidereviews.submenus.admin;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.AdminMenu;

public class ManageGameCatalogSubmenu implements Menu {

        String title = "ADMIN MENU > Manage game catalog";
        String[] menuItems = {"Add game", "Remove game", "Update game", "Add sale", "View game catalog"};

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
                case 5 -> System.out.println("You selected Option 5.");
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
