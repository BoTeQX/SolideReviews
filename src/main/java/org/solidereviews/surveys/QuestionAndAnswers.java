package org.solidereviews.surveys;

import java.util.ArrayList;

public class QuestionAndAnswers {
  private String question;
  private ArrayList<String> answers = new ArrayList<>();

  private boolean multipleChoice;
  private ArrayList<String> choices = new ArrayList<>();

  public QuestionAndAnswers(String question, boolean multipleChoice) {
    this.question = question;
    this.multipleChoice = multipleChoice;
  }

  public String getQuestion() {
    return question;
  }

  public void changeQuestion(String question) {
    this.question = question;
  }

  public void addAnswer(String answer) {
    answers.add(answer);
  }

  public ArrayList<String> getAnswers() {
    return answers;
  }

  public boolean isMultipleChoice() {
    return multipleChoice;
  }

  public void setMultipleChoice(boolean multipleChoice) {
    this.multipleChoice = multipleChoice;
  }

  public void addChoice(String choice) {
    choices.add(choice);
  }

  public ArrayList<String> getChoices() {
    return choices;
  }
}