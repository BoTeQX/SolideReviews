package org.solidereviews.reviews;

import java.util.ArrayList;

public class AllReviews {
    static ArrayList<Review> reviews = new ArrayList<>();

    public static void addReview(Review review) {
        reviews.add(review);
    }

    public static ArrayList<Review> getReviews() {
        return reviews;
    }

    public static void getReviewsByGame(String gameName) {
        for (Review review : reviews) {
            if (Review.getGameName().equals(gameName)) {
                Review.showReview();
            }
        }
    }
}