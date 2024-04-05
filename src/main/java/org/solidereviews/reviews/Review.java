package org.solidereviews.reviews;

public class Review {
    static String gameName;
    static int graphicsRating;
    static int gameplayRating;
    static int storyRating;
    static int overallRating;
    static String reviewText;

    public Review(String gameName, int graphicsRating, int gameplayRating, int storyRating,
            String reviewText) {
        this.gameName = gameName;
        this.graphicsRating = graphicsRating;
        this.gameplayRating = gameplayRating;
        this.storyRating = storyRating;
        this.overallRating = (graphicsRating + gameplayRating + storyRating) / 3;
        this.reviewText = reviewText;
    }

    public static String getGameName() {
        return gameName;
    }

    public static int getGraphicsRating() {
        return graphicsRating;
    }

    public static int getGameplayRating() {
        return gameplayRating;
    }

    public static int getStoryRating() {
        return storyRating;
    }

    public static String getReviewText() {
        return reviewText;
    }

    public static int getOverallRating() {
        return overallRating;
    }

    public static void showReview() {
        // put clear screen here
        System.out.println("Game: " + gameName);
        System.out.println("Graphics: " + graphicsRating);
        System.out.println("Gameplay: " + gameplayRating);
        System.out.println("Story: " + storyRating);
        System.out.println("Overall: " + overallRating);
        System.out.println("Review: " + reviewText);
    }

    public static void addReview(Review review) {
        AllReviews.addReview(review);
    }
}
