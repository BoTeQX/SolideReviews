package org.solidereviews.games;

import java.util.ArrayList;
import org.solidereviews.games.QuestionAndAnswers;

public class Game {
    private String name;
    private String genre;
    private double price;
    private int sale = 0;
    private ArrayList<QuestionAndAnswers> survey = new ArrayList<>();

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

    public void addToSurvey(QuestionAndAnswers qna) {
        survey.add(qna);
    }

    public ArrayList<QuestionAndAnswers> getSurvey() {
        return survey;
    }
}
