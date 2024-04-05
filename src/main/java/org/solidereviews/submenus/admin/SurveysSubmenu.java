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

    String title = "ADMIN MENU > Surveys"; // Title of the submenu
    String[] menuItems = {"Create Survey", "Update survey", "Delete survey", "Show survey result(s)"}; // Menu items

    @Override
    public String getTitle() {
        return title;
    } // Return the title of the submenu

    @Override
    public String[] getMenuItems() {
        return menuItems;
    } // Return the menu items

    // Use the user's choice that was entered in the menu to determine which method to call or menu to redirect to
    @Override
    public void processUserChoice(int choice) {
        switch (choice) {
            case 1 -> createSurvey(); // if user chooses 1, call createSurvey method (to create a survey)
            case 2 -> updateSurvey(); // if user chooses 2, call updateSurvey method (to update a survey)
            case 3 -> deleteSurvey();// if user chooses 3, call deleteSurvey method (to delete a survey)
            case 4 -> showSurvey(); // if user chooses 4, call showSurvey method (to show survey results)
            case 9 -> new AdminMenu().initiateMenu(); // if user chooses 9, the user is redirected to the AdminMenu
            case 0 -> closeProgram(); // if user chooses 0, the program is closed
            default -> System.out.println("Invalid choice. Please enter a valid option."); // if user chooses an invalid option, an error message is displayed
        }
    }


    //TODO: Implement the following methods in a new class called SurveyController
    private void createSurvey() {
        Game selectedGame = showGamesAndSelect();
        if (selectedGame == null)
            return;

        String gameName = selectedGame.getName();

        createQuestionMenu(gameName, "Enter survey question for: ", null, null, null);

        String question = scanner.nextLine();
        createQuestionMenu(gameName, null, question, null, null);
        
        boolean multipleChoice = createConfirmMenu("Is this a multiple choice question?");
        QuestionAndAnswers survey = new QuestionAndAnswers(question, multipleChoice);
        if (multipleChoice) {
            while (true) {
                createQuestionMenu(gameName, "Enter answer for the question: ", question, String.valueOf(multipleChoice), survey.getAnswers());
                scanner.nextLine();
                String answer = scanner.nextLine();
                survey.addAnswer(answer);

                createQuestionMenu(gameName, null, question, String.valueOf(multipleChoice), survey.getAnswers());
                boolean addMore = createConfirmMenu("Do you want to add more answers?");
                if (!addMore)
                    break;
            }
        }

        createQuestionMenu(gameName, null, question, String.valueOf(multipleChoice), survey.getAnswers());
        boolean save = createConfirmMenu("Do you want to save this survey?");
        if (save) {
            selectedGame.addToSurvey(survey);
            createQuestionMenu(gameName, Colors.GREEN_BOLD + "Survey created successfully!\n", question, String.valueOf(multipleChoice), survey.getAnswers());
        }

        pressToContinue();
    }

    private void updateSurvey() {
        Game selectedGame = showGamesAndSelect();
        if (selectedGame == null)
            return;

        String gameName = selectedGame.getName();

        clearScreen();
        ArrayList<QuestionAndAnswers> surveyList = selectedGame.getSurvey();
        if (!checkForSurveyList(surveyList, gameName))
            return;

        QuestionAndAnswers survey = selectSurvey(surveyList, "Select Survey to update");
        if (survey == null)
            return;
        
        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()), survey.getAnswers());
        boolean confirmed = createConfirmMenu("Are you sure you want to update this survey?");
        if (!confirmed) {
            System.out.println("\nSurvey update cancelled.");
            pressToContinue();
            return;
        }

        showThingsToUpdateAndSelect(gameName, survey);
    }

    private boolean checkForSurveyList(ArrayList<QuestionAndAnswers> surveyList, String gameName) {
        if (surveyList.size() == 0) {
            System.out.println("No survey data found for " + gameName);
            pressToContinue();
            return false;
        }
    
        return true;
    }

    private void deleteSurvey() {
        Game selectedGame = showGamesAndSelect();
        if (selectedGame == null)
            return;

        clearScreen();
        ArrayList<QuestionAndAnswers> surveyList = selectedGame.getSurvey();
        if (!checkForSurveyList(surveyList, selectedGame.getName()))
            return;

        QuestionAndAnswers survey = selectSurvey(surveyList, "Select Survey to delete");
        if (survey == null)
            return;

        createQuestionMenu(selectedGame.getName(), null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()), survey.getAnswers());
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

    private void createQuestionMenu(String gameName, String bottomText, String question, String multipleChoice, ArrayList<String> answers) {
        clearScreen();
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "Survey Question" + Colors.RESET);
        System.out.println("│");
        System.out.println("├ Game: " + Colors.GREEN_BOLD + gameName + Colors.RESET);

        if (question != null)
            System.out.println("├ Question: " + Colors.GREEN_BOLD + question + Colors.RESET);

        if (multipleChoice != null) {
            multipleChoice = multipleChoice.equals("true") ? "Yes" : "No";
            System.out.println("├ Multiple Choice: " + Colors.GREEN_BOLD + multipleChoice + Colors.RESET);
        }

        if (answers != null && answers.size() > 0 && multipleChoice != null && multipleChoice.equals("Yes")) {
            System.out.println("├ Answers: ");
            for (int i = 0; i < answers.size(); i++) {
                String symbool = "│ ├─ " + Colors.YELLOW_BRIGHT;
                if (i == answers.size() - 1)
                    symbool = "│ ╰─ " + Colors.YELLOW_BRIGHT;

                System.out.println(symbool + (i + 1) + Colors.RESET + ". " + Colors.GREEN_BOLD + answers.get(i) + Colors.RESET);
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

    private QuestionAndAnswers selectSurvey(ArrayList<QuestionAndAnswers> survey, String menuTitle) {
        clearScreen();
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + menuTitle + Colors.RESET);
        System.out.println("│");

        for (int i = 0; i < survey.size(); i++) {
            System.out.println("├ <" + Colors.BLUE_BOLD + (i + 1) + Colors.RESET + "> " + survey.get(i).getQuestion());
        }
        System.out.println("│");
        System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET + "\n");

        System.out.print(Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);

        if (scanner.hasNextInt() == false) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return selectSurvey(survey, menuTitle);
        }
    
        int surveyIndex = scanner.nextInt();
        if (surveyIndex == 0)
            return null;

        if (surveyIndex < 0 || surveyIndex > survey.size()) {
            System.out.println("Invalid choice. Please enter a valid option.");
            return selectSurvey(survey, menuTitle);
        }

        QuestionAndAnswers selectedSurvey = survey.get(surveyIndex - 1);
        if (selectedSurvey == null) {
            return selectSurvey(survey, menuTitle);
        }

        return selectedSurvey;
    }
    
    private void showThingsToUpdateAndSelect(String gameName, QuestionAndAnswers survey) {
        int maxChoices = survey.isMultipleChoice() ? 4 : 2;
        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()), survey.getAnswers());
        System.out.println("├─> " + Colors.CYAN_BOLD_BRIGHT + "Select element to Update" + Colors.RESET);
        System.out.println("│");
        System.out.println("├ <" + Colors.BLUE_BOLD + "1" + Colors.RESET + "> Update Question");
        System.out.println("├ <" + Colors.BLUE_BOLD + "2" + Colors.RESET + "> Update Multiple Choice");
        if (survey.isMultipleChoice()) {
            System.out.println("├ <" + Colors.BLUE_BOLD + "3" + Colors.RESET + "> Add Answers");
            System.out.println("├ <" + Colors.BLUE_BOLD + "4" + Colors.RESET + "> Delete Answers");
        }
        System.out.println("│");
        System.out.println("├ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + "> " + Colors.RED + "Cancel" + Colors.RESET);
        System.out.println("│");
        System.out.print("╰ " + Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);

        if (scanner.hasNextInt() == false) {
            scanner.next();
            showThingsToUpdateAndSelect(gameName, survey);
        }

        int choice = scanner.nextInt();
        if (choice == 0)
            return;

        if (choice < 0 || choice > maxChoices)
            showThingsToUpdateAndSelect(gameName, survey);

        scanner.nextLine();
        processUserChoiceSurvey(gameName, survey, choice);
    }

    private void processUserChoiceSurvey(String gameName, QuestionAndAnswers survey, int choice) {
        switch (choice) {
            case 1 -> updateQuestion(gameName, survey);
            case 2 -> updateMultipleChoice(gameName, survey);
            case 3 -> addAnswer(gameName, survey);
            case 4 -> deleteAnswer(gameName, survey);
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private void updateQuestion(String gameName, QuestionAndAnswers survey) {
        createQuestionMenu(gameName, "Enter new question: ", survey.getQuestion(), String.valueOf(survey.isMultipleChoice()), survey.getAnswers());
        survey.changeQuestion(scanner.nextLine());
        showThingsToUpdateAndSelect(gameName, survey);
    }

    private void updateMultipleChoice(String gameName, QuestionAndAnswers survey) {
        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()), survey.getAnswers());
        boolean multipleChoice = createConfirmMenu("Is this a multiple choice question?");
        survey.setMultipleChoice(multipleChoice);
        showThingsToUpdateAndSelect(gameName, survey);
    }

    private void addAnswer(String gameName, QuestionAndAnswers survey) {
        createQuestionMenu(gameName, "Enter new answer: ", survey.getQuestion(), String.valueOf(survey.isMultipleChoice()), survey.getAnswers());
        survey.addAnswer(scanner.nextLine());
        showThingsToUpdateAndSelect(gameName, survey);
    }

    private void deleteAnswer(String gameName, QuestionAndAnswers survey) {
        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()), survey.getAnswers());
        String answer = showAnswersAndSelect(survey);
        if (answer == null)
            return;

        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()), survey.getAnswers());
        boolean multipleChoice = createConfirmMenu("Are you sure you want to delete this answer '" + Colors.RESET + answer + Colors.CYAN_BOLD + "' from the survey?");
        if (multipleChoice)
            survey.getAnswers().remove(answer);
        showThingsToUpdateAndSelect(gameName, survey);
    }

    private String showAnswersAndSelect(QuestionAndAnswers survey) {
        System.out.println("├─> " + Colors.CYAN_BOLD_BRIGHT + "Select Answer to delete" + Colors.RESET);
        System.out.println("│");
        ArrayList<String> answersList = survey.getAnswers();
        for (int i = 0; i < answersList.size(); i++) {
            System.out.println("├ <" + Colors.BLUE_BOLD + (i+1) + Colors.RESET + "> " + answersList.get(i));
        }
    
        System.out.println("│");
        System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET + "\n");

        System.out.print(Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);
        if (scanner.hasNextInt() == false) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return showAnswersAndSelect(survey);
        }
    
        int choice = scanner.nextInt();
        if (choice == 0)
            return null;

        if (choice < 0 || choice > answersList.size()) {
            System.out.println("Invalid choice. Please enter a valid option.");
            return showAnswersAndSelect(survey);
        }

        String answer = answersList.get(choice - 1);
        scanner.nextLine();

        return answer;
    }
}
