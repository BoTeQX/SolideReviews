package submenus.admin;

import games.GameController;
import games.GameDisplayer;
import interfaces.Menu;
import menus.AdminMenu;

import utils.GlobalFunctions;

public class ManageGameCatalogSubmenu implements Menu {

    String title = "ADMIN MENU > Manage game catalog";
    String[] menuItems = { "Add game", "Remove game", "Update game", "Add sale", "View game catalog" };

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
            case 1 -> GameController.addGame(getTitle());
            case 2 -> GameController.removeGame();
            case 3 -> GameController.updateGame();
            case 4 -> GameController.addSale();
            case 5 -> GameDisplayer.showAllGames(getTitle());
            case 9 -> new AdminMenu().initiateMenu();
            case 0 -> GlobalFunctions.closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}
