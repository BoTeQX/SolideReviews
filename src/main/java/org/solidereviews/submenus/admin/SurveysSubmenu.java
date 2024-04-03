package org.solidereviews.submenus.admin;

import java.util.ArrayList;
import java.util.Scanner;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.AdminMenu;

import org.solidereviews.utils.Colors;

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
            case 2 -> updateSurvey();
            case 3 -> deleteSurvey();
            case 4 -> showSurvey();
            case 9 -> new AdminMenu().initiateMenu();
            case 0 -> closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private void createSurvey() {
        Game selectedGame = showGamesAndSelect();
        if (selectedGame == null)
            return;

        createQuestionMenu(selectedGame, "Enter survey question for: ", null, null, null);

        String question = scanner.next();
        createQuestionMenu(selectedGame, null, question, null, null);
        
        boolean multipleChoice = createConfirmMenu("Is this a multiple choice question?");
        QuestionAndAnswers survey = new QuestionAndAnswers(question, multipleChoice);
        if (multipleChoice) {
            while (true) {
                createQuestionMenu(selectedGame, "Enter answer for the question: ", question, String.valueOf(multipleChoice), survey.getAnswers());
                scanner.nextLine();
                String answer = scanner.next();
                survey.addAnswer(answer);

                createQuestionMenu(selectedGame, null, question, String.valueOf(multipleChoice), survey.getAnswers());
                boolean addMore = createConfirmMenu("Do you want to add more answers?");
                if (!addMore)
                    break;
            }
        }

        createQuestionMenu(selectedGame, null, question, String.valueOf(multipleChoice), survey.getAnswers());
        boolean save = createConfirmMenu("Do you want to save this survey?");
        if (save) {
            selectedGame.addToSurvey(survey);
            createQuestionMenu(selectedGame, Colors.GREEN_BOLD + "Survey created successfully!", question, String.valueOf(multipleChoice), survey.getAnswers());
        }

        pressToContinue();
    }

    private void updateSurvey() {
        Game selectedGame = showGamesAndSelect();
        if (selectedGame == null)
            return;
    }

    private void deleteSurvey() {
        Game selectedGame = showGamesAndSelect();
        if (selectedGame == null)
            return;

        clearScreen();
        ArrayList<QuestionAndAnswers> surveyList = selectedGame.getSurvey();
        if (surveyList.size() == 0) {
            System.out.println("No survey data found for " + selectedGame.getName());
            pressToContinue();
            return;
        }

        QuestionAndAnswers survey = selectSurveyToDelete(surveyList);
        if (survey == null)
            return;

        createQuestionMenu(selectedGame, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()), survey.getAnswers());
        boolean delete = createConfirmMenu("Are you sure you want to delete this survey?");
        if (delete) {
            selectedGame.removeFromSurvey(survey);
            System.out.println("\nSurvey deleted successfully!");
        } else {
            System.out.println("\nSurvey deletion cancelled.");
        }

        pressToContinue();
    }

    private void showSurvey() {
        Game selectedGame = showGamesAndSelect();
        if (selectedGame == null)
            return;

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

    private Game showGamesAndSelect() {
        clearScreen();
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "Select Game" + Colors.RESET);
        System.out.println("│");
        ArrayList<Game> games = new GameController().getGames();
        for (int i = 0; i < games.size(); i++) {
            System.out.println("├ <" + Colors.BLUE_BOLD + (i+1) + Colors.RESET + "> " + games.get(i).getName());
        }
        System.out.println("│");
        System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET + "\n");

        System.out.print(Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);
        if (scanner.hasNextInt() == false) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return showGamesAndSelect();
        }
    
        int gameIndex = scanner.nextInt();
        if (gameIndex == 0)
            return null;

        if (gameIndex < 0 || gameIndex > games.size()) {
            System.out.println("Invalid choice. Please enter a valid option.");
            return showGamesAndSelect();
        }

        Game selectedGame = games.get(gameIndex - 1);
        scanner.nextLine();

        return selectedGame;
    }

    private void createQuestionMenu(Game selectedGame, String bottomText, String question, String multipleChoice, ArrayList<String> answers) {
        clearScreen();
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "Survey Question" + Colors.RESET);
        System.out.println("│");
        System.out.println("├ Game: " + Colors.GREEN_BOLD + selectedGame.getName() + Colors.RESET);

        if (question != null)
            System.out.println("├ Question: " + Colors.GREEN_BOLD + question + Colors.RESET);

        if (multipleChoice != null)
            System.out.println("├ Multiple Choice: " + Colors.GREEN_BOLD + multipleChoice + Colors.RESET);

        if (answers != null && answers.size() > 0) {
            System.out.println("├ Answers: ");
            for (int i = 0; i < answers.size(); i++) {
                String symbool = "│ ├";
                if (i == answers.size() - 1)
                    symbool = "│ ╰";

                System.out.println(symbool + (i + 1) + ". " + Colors.GREEN_BOLD + answers.get(i) + Colors.RESET);
            }
        }

        System.out.println("│");

        if (bottomText != null)
            System.out.print("╰ " + Colors.BLUE_BOLD + bottomText + Colors.RESET);
    }

    private boolean createConfirmMenu(String titleMenu) {
        System.out.println("├─> " + Colors.CYAN_BOLD_BRIGHT + titleMenu + Colors.RESET);
        System.out.println("│");
        System.out.println("├ <" + Colors.BLUE_BOLD + "1" + Colors.RESET + "> Yes");
        System.out.println("├ <" + Colors.BLUE_BOLD + "2" + Colors.RESET + "> No");
        System.out.println("│");
        System.out.print("╰ " + Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);

        if (scanner.hasNextInt() == false) {
            scanner.next();
            return createConfirmMenu(titleMenu);
        }

        int choice = scanner.nextInt();
        if (choice < 0 || choice > 2)
            return createConfirmMenu(titleMenu);

        return choice == 1;
    }

    private QuestionAndAnswers selectSurveyToDelete(ArrayList<QuestionAndAnswers> survey) {
        clearScreen();
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "Select Survey to delete" + Colors.RESET);
        System.out.println("│");

        for (int i = 0; i < survey.size(); i++) {
            System.out.println("├ <" + Colors.BLUE_BOLD + (i + 1) + Colors.RESET + "> " + survey.get(i).getQuestion());
        }
        System.out.println("│");
        System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET + "\n");

        if (scanner.hasNextInt() == false) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return selectSurveyToDelete(survey);
        }
    
        int surveyIndex = scanner.nextInt();
        if (surveyIndex == 0)
            return null;

        if (surveyIndex < 0 || surveyIndex > survey.size()) {
            System.out.println("Invalid choice. Please enter a valid option.");
            return selectSurveyToDelete(survey);
        }

        QuestionAndAnswers selectedSurvey = survey.get(surveyIndex - 1);
        if (selectedSurvey == null) {
            return selectSurveyToDelete(survey);
        }

        scanner.nextLine();
        return selectedSurvey;
    }
}
