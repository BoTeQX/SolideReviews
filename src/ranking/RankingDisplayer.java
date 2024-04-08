package ranking;

import games.Game;
import games.GameController;
import reviews.Review;
import utils.Colors;
import utils.GlobalFunctions;

import java.util.ArrayList;
import java.util.Collections;

public class RankingDisplayer {

    private RankingDisplayer() {
        // Prevent instantiation
    }

    // Method to calculate the average rating for a game
    private static int calculateAverageRating(Game game) {
        ArrayList<Review> reviews = game.getReviews();
        if (reviews.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getOverallRating();
        }
        return sum / reviews.size();
    }

    public static void showRankedGames() {
        ArrayList<Game> rankedGames = new ArrayList<>(GameController.games); // Create a copy of the games list

        // Sort the games based on their average rating
        Collections.sort(rankedGames, (game1, game2) ->
                Integer.compare(calculateAverageRating(game2), calculateAverageRating(game1)));

        // Check if there are any ranked games (games with reviews)
        boolean hasRankedGames = rankedGames.stream().anyMatch(game -> calculateAverageRating(game) > 0);

        if (hasRankedGames) {
            // Display the top bar of the table
            System.out.println("+--------+------------------------------------------------------+------------+");
            System.out.println("| Ranking| Title                                                | Avg. Rating|");
            System.out.println("+--------+------------------------------------------------------+------------+");

            int ranking = 1;
            for (Game game : rankedGames) {
                int averageRating = calculateAverageRating(game);
                if (averageRating > 0) {
                    System.out.printf("| %-7d| %-52s | %-13s |%n", ranking++, game.getName(), getStars(averageRating));
                }
            }
            System.out.println("+--------+------------------------------------------------------+------------+");
        }
    }

    private static void showUnrankedGames() {
        ArrayList<Game> unrankedGames = getUnrankedGames();

        if (!unrankedGames.isEmpty()) {
            System.out.println("\n\n+------------------------------------------------------+");
            System.out.println("|                 Unranked Games                       |");
            System.out.println("+------------------------------------------------------+");

            for (Game game : unrankedGames) {
                System.out.printf("| %-52s |%n", game.getName());
            }

            System.out.println("+------------------------------------------------------+");
        }
    }

    private static ArrayList<Game> getUnrankedGames() {
        ArrayList<Game> unrankedGames = new ArrayList<>();
        for (Game game : GameController.games) {
            if (game.getReviews().isEmpty()) {
                unrankedGames.add(game);
            }
        }
        return unrankedGames;
    }

    private static String getStars(int rating) {
        StringBuilder stars = new StringBuilder();
        // Append white stars for the entire length
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                // For ratings less than or equal to the actual rating, append yellow stars
                stars.append(Colors.YELLOW_BOLD_BRIGHT).append("★ ").append(Colors.RESET);
            } else {
                // For ratings greater than the actual rating, append white stars
                stars.append("★ ");
            }
        }
        return stars.toString();
    }

    public static void displayFullRanking() {
        GlobalFunctions.clearScreen();
        showRankedGames();
        showUnrankedGames();
        GlobalFunctions.pressToContinue();
    }
}
