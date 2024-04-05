package org.solidereviews.submenus.games;

import org.solidereviews.games.GameController;
import org.solidereviews.games.GameDisplayer;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.GamesMenu;

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
            case 1 -> GameDisplayer.showAllGames(getTitle()); // if user chooses 1, call showAllGames method (to display all games in the catalog) (getTitle() is used to pass the title of the submenu to later know where the user came from)
            case 2 -> GameDisplayer.chooseGenreAndShowGames(getTitle()); // if user chooses 2, call chooseGenreAndShowGames method (to display games by genre) (getTitle() is used to pass the title of the submenu to later know where the user came from)
            case 9 -> new GamesMenu().initiateMenu(); // if user chooses 9, the user is redirected to the GamesMenu
            case 0 -> closeProgram(); // if user chooses 0, the program is closed
            default -> System.out.println("Invalid choice. Please enter a valid option."); // if user chooses an invalid option, an error message is displayed
        }
    }
}
