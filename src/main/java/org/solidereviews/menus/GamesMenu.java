package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.games.GameReviewsSubmenu;
import org.solidereviews.submenus.games.GamesCatalogSubmenu;

public class GamesMenu implements Menu {
    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> option1();
            case 2 -> option2();
            case 9 -> backToPreviousMenu();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }


    private void option1() {
        Menu menu = new GamesCatalogSubmenu();
        String title = "GAMES MENU > Game catalog";
        String[] menuItems = {"Show all games", "Show games (genre)", "Show games (rating)"};
        switchToMenu(menu, title, menuItems);
    }

    private void option2() {
        Menu menu = new GameReviewsSubmenu();
        String title = "GAMES MENU > Game reviews";
        String[] menuItems = {"Show all reviews", "Show reviews (game)", "Review game"};
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
