package org.solidereviews.reviews;

public class Review {
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
