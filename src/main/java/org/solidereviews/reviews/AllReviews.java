package org.solidereviews.reviews;

import java.util.ArrayList;

public class AllReviews {
    static ArrayList<Review> reviews = new ArrayList<>();

    public static void addReview(Review review) {
        reviews.add(review);
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}