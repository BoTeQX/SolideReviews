package org.solidereviews.submenus.admin;

import java.util.ArrayList;
import java.util.Scanner;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.AdminMenu;

import org.solidereviews.games.Game;
import org.solidereviews.games.GameController;
import org.solidereviews.games.QuestionAndAnswers;

public class SurveysSubmenu implements Menu {

    String title = "ADMIN MENU > Surveys";
    String[] menuItems = {"Create Survey", "Update survey", "Delete survey", "Show survey result(s)"};

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String[] getMenuItems() {
        return menuItems;
    }

    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> createSurvey();
            case 2 -> System.out.println("You selected Option 2.");
            case 3 -> deleteSurvey();
            case 4 -> showSurvey();
            case 9 -> new AdminMenu().initiateMenu();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private void createSurvey() {
        clearScreen();
    
        System.out.println("Select game to create survey for: ");
        ArrayList<Game> games = new GameController().getGames();
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i + 1 + ". " + games.get(i).getName());
        }
        int gameIndex = scanner.nextInt();
        Game selectedGame = games.get(gameIndex - 1);

        clearScreen();
        System.out.println("Do you want to create survey for " + selectedGame.getName() + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        if (scanner.nextInt() != 1) {
            System.out.println("Survey creation cancelled.");
            return;
        }

        clearScreen();
        System.out.println("Enter survey question for " + selectedGame.getName() + ":");
        scanner.nextLine();
        String question = scanner.next();

        clearScreen();
        System.out.println("Is this a multiple choice question?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        boolean multipleChoice = scanner.nextInt() == 1;

        clearScreen();
        QuestionAndAnswers survey = new QuestionAndAnswers(question, multipleChoice);
        selectedGame.addToSurvey(survey);
        System.out.println("Survey created successfully!");
          pressToContinue();
    }

    private void deleteSurvey() {
        clearScreen();
        System.out.println("Select game to show survey for: ");
        ArrayList<Game> games = new GameController().getGames();
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i + 1 + ". " + games.get(i).getName());
        }

        int gameIndex = scanner.nextInt();
        Game selectedGame = games.get(gameIndex - 1);

        clearScreen();
        ArrayList<QuestionAndAnswers> survey = selectedGame.getSurvey();
        if (survey.size() == 0) {
            System.out.println("No data found for " + selectedGame.getName());
            pressToContinue();
            return;
        }
    
        System.out.println("Select survey to delete for " + selectedGame.getName() + ": ");
        for (int i = 0; i < survey.size(); i++) {
            System.out.println(i + 1 + ". " + survey.get(i).getQuestion());
        }

        int surveyIndex = scanner.nextInt();
        if (survey.get(surveyIndex - 1) != null) {
            survey.remove(surveyIndex - 1);
            System.out.println("delted");
        }
        pressToContinue();
    }

    private void showSurvey() {
        clearScreen();
        System.out.println("Select game to show survey for: ");
        ArrayList<Game> games = new GameController().getGames();
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i + 1 + ". " + games.get(i).getName());
        }

        int gameIndex = scanner.nextInt();
        Game selectedGame = games.get(gameIndex - 1);

        clearScreen();
        ArrayList<QuestionAndAnswers> survey = selectedGame.getSurvey();
        if (survey.size() == 0) {
            System.out.println("No data found for " + selectedGame.getName());
            pressToContinue();
            return;
        }
    
        for (QuestionAndAnswers qna : selectedGame.getSurvey()) {
            System.out.println("\nQuestion: " + qna.getQuestion() + " MultipleChoice: " + qna.isMultipleChoice());
            System.out.println("------- Answers");
        }
    
        pressToContinue();
    }
}
