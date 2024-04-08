package org.solidereviews.submenus.games;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.GamesMenu;
import org.solidereviews.reviews.ReviewController;
import org.solidereviews.reviews.ReviewDisplayer;

import org.solidereviews.utils.GlobalFunctions;

public class GameReviewsSubmenu implements Menu {

    String title = "GAMES MENU > Game reviews";
    String[] menuItems = { "Show reviews (game)", "Review game" };

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
            case 1 -> new ReviewDisplayer().showReviewsByGame();
            case 2 -> new ReviewController().addReview();
            case 9 -> new GamesMenu().initiateMenu();
            case 0 -> GlobalFunctions.closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}
