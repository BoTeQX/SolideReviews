package reviews;

import java.util.ArrayList;

import games.Game;
import games.GameController;
import submenus.games.GameReviewsSubmenu;
import utils.GlobalFunctions;

public class ReviewDisplayer {
    public void showReviewsByGame() {
        Game game = GameController.showGamesAndSelect("Select a game to see the reviews from:");
        if (game ==  null) {
            new GameReviewsSubmenu().initiateMenu();
            return;
        }
        int overall = game.getOverallRating();
        System.err.println("Overall rating: " + overall);
        ArrayList<Review> reviews = game.getReviews();
        if (reviews.isEmpty()) {
            System.out.println("No reviews found for this game.");
        } else {
            System.out.printf("Reviews for %s: %n", game.getName());
            for (Review review : reviews) {
                System.out.println("--------------------------------------------------");
                review.showReview();
            }
        }
        GlobalFunctions.pressToContinue();
    }
}
