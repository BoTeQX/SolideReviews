package submenus.games;

import interfaces.Menu;
import menus.GamesMenu;
import reviews.ReviewController;
import reviews.ReviewDisplayer;

import utils.GlobalFunctions;

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
