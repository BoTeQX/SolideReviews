package org.solidereviews.submenus.games;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.GamesMenu;

public class GameReviewsSubmenu implements Menu {

    String title = "GAMES MENU > Game reviews";
    String[] menuItems = {"Show all reviews", "Show reviews (game)", "Review game"};

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
            case 9 -> backToPreviousMenu();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    @Override
    public void backToPreviousMenu() {
        Menu menu = new GamesMenu();
       menu.initiateMenu();
    }
}
