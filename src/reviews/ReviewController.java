package reviews;

import java.util.ArrayList;
import java.util.Scanner;
import games.Game;
import games.GameController;
import submenus.games.GameReviewsSubmenu;
import utils.FileManager;
import utils.GlobalFunctions;

public class ReviewController {
    public static FileManager fileManager = new FileManager();

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
        GlobalFunctions.clearScreen();
        Game game = GameController.showGamesAndSelect("Select a game to review:");
        GlobalFunctions.clearScreen();
        System.out.printf("Reviewing %s: %n", game.getName());
        int graphicsRating = setRating("graphics");
        int gameplayRating = setRating("gameplay");
        int storyRating = setRating("story");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your review or leave empty:");
        String reviewText = scanner.nextLine();
        Review review = new Review(graphicsRating, gameplayRating, storyRating, reviewText);
        String reviewString = game.getName() + "~" + graphicsRating + "~" +  gameplayRating +  "~" + storyRating + "~" + reviewText;
        GlobalFunctions.clearScreen();
        System.out.printf("Review preview for %s: %n", game.getName());
        review.showReview();
        System.out.println("Confirm review? (Y/N)");
        String confirm = scanner.nextLine();
        while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
            System.out.println("Invalid input. Please enter Y or N:");
            confirm = scanner.nextLine();
        }
        GlobalFunctions.clearScreen();
        if (confirm.equalsIgnoreCase("N")) {
            System.out.println("Review cancelled.");
        } else {
            writeReview(reviewString);
            game.addReview(review);
            System.out.println("Review added successfully!");
        }
        GlobalFunctions.pressToContinue();
        new GameReviewsSubmenu().initiateMenu();
    }

    public static void initiateReviews(Game game) {
        ArrayList<String> reviewData = fileManager.readReviewFile();
        for (String data : reviewData) {
            String[] parts = data.split("~");
            String gameName = parts[0];
            if (gameName.equals(game.getName())) {
            int graphicsRating = Integer.parseInt(parts[1]);
            int gameplayRating = Integer.parseInt(parts[2]);
            int storyRating = Integer.parseInt(parts[3]);
            String reviewText = "";
            if (parts.length > 4) {
                    reviewText = parts[4];
                }
            Review review = new Review(graphicsRating, gameplayRating, storyRating, reviewText);
            game.addReview(review);
         }
        }
    }

    public void writeReview(String reviewString){
        fileManager.writeReviewToFile(reviewString);
    }
}
