package org.solidereviews.reviews;

import java.util.Scanner;
import java.util.ArrayList;

import org.solidereviews.games.Game;
import org.solidereviews.games.GameController;
import org.solidereviews.submenus.games.GameReviewsSubmenu;

public class ReviewController {

    public void addReview() {
        ArrayList<Game> games = GameController.getGames();
        ArrayList<String> gameNames = new ArrayList<>();
        for (Game game : games) {
            gameNames.add(game.getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a game to review:");
        String gameName = scanner.nextLine();
        while (!gameNames.contains(gameName)) {
            System.out.println("Invalid game name. Please select a game from the list:");
            gameName = scanner.nextLine();
        }

        System.out.println("Rate the graphics (1-5):");
        int graphicsRating = scanner.nextInt();
        while (graphicsRating < 1 || graphicsRating > 5) {
            System.out.println("Invalid rating. Please enter a number between 1 and 5:");
            graphicsRating = scanner.nextInt();
        }

        System.out.println("Rate the gameplay (1-5):");
        int gameplayRating = scanner.nextInt();
        while (gameplayRating < 1 || gameplayRating > 5) {
            System.out.println("Invalid rating. Please enter a number between 1 and 5:");
            gameplayRating = scanner.nextInt();
        }

        System.out.println("Rate the story (1-5):");
        int storyRating = scanner.nextInt();
        while (storyRating < 1 || storyRating > 5) {
            System.out.println("Invalid rating. Please enter a number between 1 and 5:");
            storyRating = scanner.nextInt();
        }

        scanner.nextLine();
        System.out.println("Write your review or leave empty:");
        scanner.nextLine();
        String reviewText = scanner.nextLine();
        Review review = new Review(gameName, graphicsRating, gameplayRating, storyRating, reviewText);

        Review.showReview();

        System.out.println("Confirm review? (Y/N)");
        String confirm = scanner.nextLine();
        while (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N")) {
            System.out.println("Invalid input. Please enter Y or N:");
            confirm = scanner.nextLine();
        }
        if (confirm.equalsIgnoreCase("N")) {
            System.out.println("Review cancelled.");
        } else {
            scanner.close();
            Review.addReview(review);
            System.out.println("Review added successfully!");

        }
        new GameReviewsSubmenu().initiateMenu();
    }
}
