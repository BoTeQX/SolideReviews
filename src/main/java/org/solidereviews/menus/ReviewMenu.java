package org.solidereviews.menus;

import java.util.Scanner;

import org.solidereviews.games.Game;
import org.solidereviews.games.GameController;

import java.util.ArrayList;

public class ReviewMenu {
    class Review {
        String gameName;
        int graphicsRating;
        int gameplayRating;
        int storyRating;
        String reviewText;
        int overallRating;

        public Review(String gameName, int graphicsRating, int gameplayRating, int storyRating, String reviewText) {
            this.gameName = gameName;
            this.graphicsRating = graphicsRating;
            this.gameplayRating = gameplayRating;
            this.storyRating = storyRating;
            this.reviewText = reviewText;
            this.overallRating = (graphicsRating + gameplayRating + storyRating) / 3;
        }

    }

    public void addReview() {
        ArrayList<Game> games = GameController.getAllGames();
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

        scanner.close();
        Review review = new Review(gameName, graphicsRating, gameplayRating, storyRating, reviewText);
        System.out.println("Review added successfully!");

    }
}
