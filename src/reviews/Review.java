package org.solidereviews.reviews;

public class Review {
    private final int graphicsRating;
    private final int gameplayRating;
    private final int storyRating;
    private final int overallRating;
    private final String reviewText;

    public Review(int graphicsRating, int gameplayRating, int storyRating,
            String reviewText) {
        this.graphicsRating = graphicsRating;
        this.gameplayRating = gameplayRating;
        this.storyRating = storyRating;
        this.overallRating = (graphicsRating + gameplayRating + storyRating) / 3;
        this.reviewText = reviewText;
    }

    public int getGraphicsRating() {
        return graphicsRating;
    }

    public int getGameplayRating() {
        return gameplayRating;
    }

    public int getStoryRating() {
        return storyRating;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void showReview() {
        System.out.println("Graphics: " + graphicsRating);
        System.out.println("Gameplay: " + gameplayRating);
        System.out.println("Story: " + storyRating);
        System.out.println("Overall: " + overallRating);
        System.out.println("Review: " + reviewText);
    }
}
