package org.solidereviews.submenus.admin;

import org.solidereviews.games.GameController;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.AdminMenu;

public class ManageGameCatalogSubmenu implements Menu {


    GameController gameController = new GameController();
        @Override
        public void processUserChoice(int choice) {
            switch (choice) {
                case 1 -> gameController.addGame();
                case 2 -> gameController.removeGame();
                case 3 -> gameController.updateGame();
                case 4 -> gameController.addSale();
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
