package org.solidereviews.games;

import org.solidereviews.games.QuestionAndAnswers;
import java.util.ArrayList;

public class Game {
    private String name;
    private String genre;
    private double price;
    private int sale = 0;
    private ArrayList<QuestionAndAnswers> enquete = new ArrayList<>();
    //private ArrayList<Review> reviews = new ArrayList<>();

    public Game(String name, String genre, double price){
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

    public ArrayList<QuestionAndAnswers> getEnquete() {
        return enquete;
    }

    public void addEnquete(QuestionAndAnswers enquete) {
        this.enquete.add(enquete);
    }

    // public ArrayList<Review> getReviews() {
    //     return reviews;
    // }

    // public void addReview(Review review) {
    //     reviews.add(review);
    // }
}
