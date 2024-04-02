package org.solidereviews.submenus.games;

import org.solidereviews.games.GameController;
import org.solidereviews.games.GameDisplayer;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.GamesMenu;

public class GamesCatalogSubmenu implements Menu {

    String title = "GAMES MENU > Game catalog";
    String[] menuItems = {"Show all games", "Show games (genre)"};

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
                case 1 -> GameDisplayer.showAllGames(getTitle());
                case 2 -> GameDisplayer.chooseGenreAndShowGames();
                case 3 -> System.out.println("You selected Option 3.");
                case 9 -> new GamesMenu().initiateMenu();
                case 0 -> closeProgram();
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
}
