package org.solidereviews.reviews;

import java.util.Scanner;
import org.solidereviews.games.Game;
import org.solidereviews.games.GameController;
import org.solidereviews.submenus.games.GameReviewsSubmenu;
import org.solidereviews.utils.Util;

public class ReviewController {
    private static int setRating(String ratingType) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Rate the %s (1-5): %n", ratingType);
        while (true) {
            if (scanner.hasNextInt()) {
                int rating = scanner.nextInt();
                if (rating >= 1 && rating <= 5) {
                    scanner.nextLine();
                    return rating;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                }
            } else {
                scanner.next();
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
    }

    public void addReview() {
        Util.clearScreen();
        Game game = GameController.showGamesAndSelect("Select a game to review:");
        Util.clearScreen();
        System.out.printf("Reviewing %s: %n", game.getName());
        int graphicsRating = setRating("graphics");
        int gameplayRating = setRating("gameplay");
        int storyRating = setRating("story");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your review or leave empty:");
        String reviewText = scanner.nextLine();
        Review review = new Review(graphicsRating, gameplayRating, storyRating, reviewText);
        Util.clearScreen();
        System.out.printf("Review preview for %s: %n", game.getName());
        review.showReview();
        System.out.println("Confirm review? (Y/N)");
        String confirm = scanner.nextLine();
        while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
            System.out.println("Invalid input. Please enter Y or N:");
            confirm = scanner.nextLine();
        }
        Util.clearScreen();
        if (confirm.equalsIgnoreCase("N")) {
            System.out.println("Review cancelled.");
        } else {
            game.addReview(review);
            System.out.println("Review added successfully!");
        }
        Util.pressToContinue();
        new GameReviewsSubmenu().initiateMenu();
    }
}
