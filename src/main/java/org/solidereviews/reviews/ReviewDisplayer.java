package org.solidereviews.reviews;

import java.util.ArrayList;

import org.solidereviews.games.Game;
import org.solidereviews.games.GameController;
import org.solidereviews.utils.GlobalFunctions;

public class ReviewDisplayer {
    public void showReviewsByGame() {
        Game game = GameController.showGamesAndSelect("Select a game to see the reviews from:");
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
