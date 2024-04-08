package submenus.games;

import interfaces.Menu;
import menus.GamesMenu;
import reviews.ReviewController;
import reviews.ReviewDisplayer;

import utils.GlobalFunctions;

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
