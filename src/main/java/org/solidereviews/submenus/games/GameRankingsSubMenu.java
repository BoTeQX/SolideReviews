package org.solidereviews.submenus.games;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.GamesMenu;

public class GameRankingsSubMenu implements Menu {

        String title = "GAMES MENU > Game rankings"; // Title of the submenu
        String[] menuItems = {"Show all rankings (descending)", "Show all rankings (ascending)", "Show ranking (game)"}; // Menu items

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
                case 1 -> System.out.println("You selected Option 1.");
                case 2 -> System.out.println("You selected Option 2.");
                case 3 -> System.out.println("You selected Option 3.");
                case 9 -> new GamesMenu().initiateMenu(); // if user chooses 9, the user is redirected to the GamesMenu
                case 0 -> closeProgram(); // if user chooses 0, the program is closed
                default -> System.out.println("Invalid choice. Please enter a valid option."); // if user chooses an invalid option, an error message is displayed
            }
        }

}
