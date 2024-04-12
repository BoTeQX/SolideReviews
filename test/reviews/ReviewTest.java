package reviews;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    @Test
    public void getGraphicsRating() {
        Review review = new Review(2, 5, 5, "This is a review");
        assertEquals(2, review.getGraphicsRating());
    }

    @Test
    public void getGameplayRating() {
        Review review = new Review(5, 4, 5, "This is a review");
        assertEquals(4, review.getGameplayRating());
    }

    @Test
    public void getStoryRating() {
        Review review = new Review(5, 5, 7, "This is a review");
        assertEquals(7, review.getStoryRating());
    }

    @Test
    public void getOverallRating() {
        Review review = new Review(1, 2, 3, "This is a review");
        assertEquals(2, review.getOverallRating());
    }

    @Test
    public void getReviewText() {
        Review review = new Review(1, 2, 3, "This is a review");
        assertEquals("This is a review", review.getReviewText());
    }
}