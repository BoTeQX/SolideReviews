package games;

import java.util.ArrayList;
import surveys.QuestionAndAnswers;
import reviews.Review;

public class Game {
    private String name;
    private String genre;
    private double price;
    private int sale = 0;
    private ArrayList<Review> reviews = new ArrayList<>();
    private ArrayList<QuestionAndAnswers> survey = new ArrayList<>();

    public Game(String name, String genre, double price) {
        this.name = name;
        this.genre = genre;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public int getOverallRating() {
        if (reviews.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getOverallRating();
        }
        return sum / reviews.size();
    }

    public void addToSurvey(QuestionAndAnswers qna) {
        survey.add(qna);
    }

    public void removeFromSurvey(QuestionAndAnswers qna) {
        survey.remove(qna);
    }

    public ArrayList<QuestionAndAnswers> getSurvey() {
        return survey;
    }
}
