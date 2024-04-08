package org.solidereviews.submenus.games;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.GamesMenu;
import org.solidereviews.reviews.ReviewController;
import org.solidereviews.reviews.ReviewDisplayer;

import org.solidereviews.utils.GlobalFunctions;

public class GameReviewsSubmenu implements Menu {

    String title = "GAMES MENU > Game reviews"; // Title of the submenu
    String[] menuItems = { "Show reviews (game)", "Review game" }; // Menu items

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
            case 1 -> new ReviewDisplayer().showReviewsByGame();
            case 2 -> new ReviewController().addReview();
            case 9 -> new GamesMenu().initiateMenu();
            case 0 -> GlobalFunctions.closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}
