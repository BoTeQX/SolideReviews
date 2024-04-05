package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.games.GameRankingsSubMenu;
import org.solidereviews.submenus.games.GameReviewsSubmenu;
import org.solidereviews.submenus.games.GamesCatalogSubmenu;

public class GamesMenu implements Menu {

    String title = "GAMES MENU"; // Title of the menu
    String[] menuItems = {"Game rankings", "Game catalog", "Game reviews"}; // Menu items

    @Override
    public String getTitle() {
        return title;
    } // Return the title of the menu

    @Override
    public String[] getMenuItems() {
        return menuItems;
    } // Return the menu items

    // Use the user's choice that was entered in the menu to determine which submenu to initiate
    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> new GameRankingsSubMenu().initiateMenu(); // If the user selects option 1, the GameRankingsSubMenu gets initiated
            case 2 -> new GamesCatalogSubmenu().initiateMenu(); // If the user selects option 2, the GamesCatalogSubmenu gets initiated
            case 3 -> new GameReviewsSubmenu().initiateMenu(); // If the user selects option 3, the GameReviewsSubmenu gets initiated
            case 9 -> new MainMenu().initiateMenu(); // If the user selects option 9, the MainMenu gets initiated
            case 0 -> closeProgram(); // If the user selects option 0, the program gets closed
            default -> System.out.println("Invalid choice. Please enter a valid option."); // If the user enters an invalid option, a message gets displayed
        }
    }

}
