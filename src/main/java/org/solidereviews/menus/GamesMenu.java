package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.games.GameRankingsSubMenu;
import org.solidereviews.submenus.games.GameReviewsSubmenu;
import org.solidereviews.submenus.games.GamesCatalogSubmenu;

import org.solidereviews.utils.GlobalFunctions;

public class GamesMenu implements Menu {

    String title = "GAMES MENU";
    String[] menuItems = { "Game rankings", "Game catalog", "Game reviews" };

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
            case 1 -> new GameRankingsSubMenu().initiateMenu();
            case 2 -> new GamesCatalogSubmenu().initiateMenu();
            case 3 -> new GameReviewsSubmenu().initiateMenu();
            case 9 -> new MainMenu().initiateMenu();
            case 0 -> GlobalFunctions.closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}
