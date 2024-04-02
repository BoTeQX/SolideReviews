package org.solidereviews.menus;

import java.util.Scanner;
import java.util.ArrayList;

public class ReviewMenu {
    class Review {
        String gameName;
        int graphicsRating;
        int gameplayRating;
        int storyRating;
        String reviewText;

        public Review(String gameName, int graphicsRating, int gameplayRating, int storyRating, String reviewText) {
            this.gameName = gameName;
            this.graphicsRating = graphicsRating;
            this.gameplayRating = gameplayRating;
            this.storyRating = storyRating;
            this.reviewText = reviewText;
        }
    }

    public void addReview() {
        ArrayList<String> gameNames = new ArrayList<>();
        gameNames.add("The Witcher 3: Wild Hunt");
        gameNames.add("Red Dead Redemption 2");
        gameNames.add("The Last of Us Part II");
        gameNames.add("God of War");
        gameNames.add("Cyberpunk 2077");

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
