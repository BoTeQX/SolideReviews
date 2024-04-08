package org.solidereviews.submenus.games;

import org.solidereviews.games.GameDisplayer;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.GamesMenu;

import org.solidereviews.utils.GlobalFunctions;

public class GamesCatalogSubmenu implements Menu {

    String title = "GAMES MENU > Game catalog"; // Title of the submenu
    String[] menuItems = { "Show all games", "Show games (genre)" }; // Menu items

    @Override
    public String getTitle() {
        return title;
    } // Return the title of the submenu

    @Override
    public String[] getMenuItems() {
        return menuItems;
    } // Return the menu items

    // Use the user's choice that was entered in the menu to determine which method to call or menu to redirect to
    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> GameDisplayer.showAllGames(getTitle());
            case 2 -> GameDisplayer.chooseGenreAndShowGames(getTitle());
            case 3 -> System.out.println("You selected Option 3.");
            case 9 -> new GamesMenu().initiateMenu();
            case 0 -> GlobalFunctions.closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}
