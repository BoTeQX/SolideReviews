package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.games.GameCatalogSubmenu;

public class GamesMenu implements Menu {
    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> option1();
            case 2 -> System.out.println("You selected Option 2.");
            case 3 -> System.out.println("You selected Option 3.");
            case 9 -> backToPreviousMenu();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }


    private void option1() {
        Menu menu = new GameCatalogSubmenu();
        String title = "GAMES MENU - game catalog";
        String[] menuItems = {"Option 1", "Option 2", "Option 3"};
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
