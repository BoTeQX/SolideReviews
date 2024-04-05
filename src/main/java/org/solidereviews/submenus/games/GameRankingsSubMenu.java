package org.solidereviews.submenus.games;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.GamesMenu;

import org.solidereviews.utils.Colors;
import org.solidereviews.utils.GlobalFunctions;

public class GameRankingsSubMenu implements Menu {

        String title = "GAMES MENU > Game rankings";
        String[] menuItems = {"Show all rankings (descending)", "Show all rankings (ascending)", "Show ranking (game)"};

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
                case 9 -> new GamesMenu().initiateMenu();
                case 0 -> GlobalFunctions.closeProgram();
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

}
