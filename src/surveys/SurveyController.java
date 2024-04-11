package surveys;

import java.util.ArrayList;
import java.util.Scanner;

import games.Game;
import games.GameController;

import utils.Colors;
import utils.GlobalFunctions;

public class SurveyController {

    private SurveyController() {
        // Prevent instantiation
    }

    static Scanner scanner = new Scanner(System.in);

    public static void createSurvey() {
        Game selectedGame = GameController.showGamesAndSelect("Select Game");
        if (selectedGame == null)
            return;

        String gameName = selectedGame.getName();

        createQuestionMenu(gameName, "Enter new question for survey: ", null, null, null);

        String question = scanner.nextLine();
        createQuestionMenu(gameName, null, question, null, null);

        boolean multipleChoice = GlobalFunctions.createConfirmMenu("Is this a multiple choice question?");
        QuestionAndAnswers survey = new QuestionAndAnswers(question, multipleChoice);
        if (multipleChoice) {
            while (true) {
                createQuestionMenu(gameName, "Enter extra choice for the question: ", question,
                        String.valueOf(multipleChoice), survey.getChoices());
                String answer = scanner.nextLine();
                survey.addChoice(answer);

                createQuestionMenu(gameName, null, question, String.valueOf(multipleChoice), survey.getChoices());
                if (!GlobalFunctions.createConfirmMenu("Do you want to add more choices?"))
                    break;
            }
        }

        createQuestionMenu(gameName, null, question, String.valueOf(multipleChoice), survey.getChoices());
        if (GlobalFunctions.createConfirmMenu("Do you want to save this survey?")) {
            selectedGame.addToSurvey(survey);
            createQuestionMenu(gameName, Colors.GREEN_BOLD + "Survey created successfully!\n", question,
                    String.valueOf(multipleChoice), survey.getChoices());
        }

        GlobalFunctions.pressToContinue();
    }

    public static void updateSurvey() {
        Game selectedGame = GameController.showGamesAndSelect("Select Game");
        if (selectedGame == null)
            return;

        String gameName = selectedGame.getName();

        GlobalFunctions.clearScreen();
        ArrayList<QuestionAndAnswers> surveyList = selectedGame.getSurvey();
        if (!checkForSurveyList(surveyList, gameName))
            return;

        QuestionAndAnswers survey = selectSurvey(surveyList, "Select Survey to update");
        if (survey == null)
            return;

        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()),
                survey.getChoices());
        showThingsToUpdateAndSelect(gameName, survey);
    }

    public static boolean checkForSurveyList(ArrayList<QuestionAndAnswers> surveyList, String gameName) {
        if (surveyList.size() == 0) {
            System.out.println("No survey data found for " + gameName);
            GlobalFunctions.pressToContinue();
            return false;
        }

        return true;
    }

    public static void deleteSurvey() {
        Game selectedGame = GameController.showGamesAndSelect("Select Game");
        if (selectedGame == null)
            return;

        GlobalFunctions.clearScreen();
        ArrayList<QuestionAndAnswers> surveyList = selectedGame.getSurvey();
        if (!checkForSurveyList(surveyList, selectedGame.getName()))
            return;

        QuestionAndAnswers survey = selectSurvey(surveyList, "Select Survey to delete");
        if (survey == null)
            return;

        createQuestionMenu(selectedGame.getName(), null, survey.getQuestion(),
                String.valueOf(survey.isMultipleChoice()), survey.getChoices());
        if (GlobalFunctions.createConfirmMenu("Are you sure you want to delete this survey?")) {
            selectedGame.removeFromSurvey(survey);
            System.out.println("\nSurvey deleted successfully!");
        } else {
            System.out.println("\nSurvey deletion cancelled.");
        }

        GlobalFunctions.pressToContinue();
    }

    public static void showSurveyResult() {
        Game selectedGame = GameController.showGamesAndSelect("Select Game");
        if (selectedGame == null)
            return;

        GlobalFunctions.clearScreen();
        ArrayList<QuestionAndAnswers> surveyList = selectedGame.getSurvey();
        if (!checkForSurveyList(surveyList, selectedGame.getName()))
            return;

        for (QuestionAndAnswers survey : surveyList) {
            createQuestionMenu(selectedGame.getName(), null, survey.getQuestion(),
                    String.valueOf(survey.isMultipleChoice()), null);
            ArrayList<String> answers = survey.getAnswers();
            if (answers.size() == 0) {
                System.out.println("No answers found for this survey.");
                GlobalFunctions.pressToContinue();
                continue;
            }

            System.out.println("├─> Answers: ");
            for (int i = 0; i < answers.size(); i++) {
                System.out.println("├ <" + Colors.YELLOW_BOLD + (i + 1) + Colors.RESET + "> " + answers.get(i));
            }

            System.out.println("╰");

            GlobalFunctions.pressToContinue();
        }
    }

    public static void createQuestionMenu(String gameName, String bottomText, String question, String multipleChoice,
            ArrayList<String> answers) {
        GlobalFunctions.clearScreen();
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
            System.out.println("├ Choices: ");
            for (int i = 0; i < answers.size(); i++) {
                String symbool = "│ ├─ " + Colors.YELLOW_BRIGHT;
                if (i == answers.size() - 1)
                    symbool = "│ ╰─ " + Colors.YELLOW_BRIGHT;

                System.out.println(
                        symbool + (i + 1) + Colors.RESET + ". " + Colors.GREEN_BOLD + answers.get(i) + Colors.RESET);
            }
        }

        System.out.println("│");

        if (bottomText != null)
            System.out.print("╰ " + Colors.BLUE_BOLD + bottomText + Colors.RESET);
    }

    public static QuestionAndAnswers selectSurvey(ArrayList<QuestionAndAnswers> survey, String menuTitle) {
        GlobalFunctions.clearScreen();
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + menuTitle + Colors.RESET);
        System.out.println("│");

        for (int i = 0; i < survey.size(); i++) {
            System.out.println("├ <" + Colors.BLUE_BOLD + (i + 1) + Colors.RESET + "> " + survey.get(i).getQuestion());
        }

        showCancelMenu();

        if (!scanner.hasNextInt()) {
            System.out.println(GlobalFunctions.ERROR_INVALID_NUMBER);
            scanner.next();
            return selectSurvey(survey, menuTitle);
        }

        int surveyIndex = scanner.nextInt();
        if (surveyIndex == 0)
            return null;

        if (surveyIndex < 0 || surveyIndex > survey.size()) {
            System.out.println(GlobalFunctions.ERROR_INVALID_CHOICE);
            return selectSurvey(survey, menuTitle);
        }

        QuestionAndAnswers selectedSurvey = survey.get(surveyIndex - 1);
        if (selectedSurvey == null) {
            return selectSurvey(survey, menuTitle);
        }

        return selectedSurvey;
    }

    public static void showThingsToUpdateAndSelect(String gameName, QuestionAndAnswers survey) {
        int maxSelections = survey.isMultipleChoice() ? 4 : 2;
        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()),
                survey.getChoices());
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

        if (!scanner.hasNextInt()) {
            scanner.next();
            showThingsToUpdateAndSelect(gameName, survey);
        }

        int selectedNumer = scanner.nextInt();
        if (selectedNumer == 0)
            return;

        if (selectedNumer < 0 || selectedNumer > maxSelections)
            showThingsToUpdateAndSelect(gameName, survey);

        scanner.nextLine();
        processUserChoiceSurvey(gameName, survey, selectedNumer);
    }

    public static void processUserChoiceSurvey(String gameName, QuestionAndAnswers survey, int choice) {
        switch (choice) {
            case 1 -> updateQuestion(gameName, survey);
            case 2 -> updateMultipleChoice(gameName, survey);
            case 3 -> addChoice(gameName, survey);
            case 4 -> deleteChoice(gameName, survey);
            default -> System.out.println(GlobalFunctions.ERROR_INVALID_CHOICE);
        }
    }

    public static void updateQuestion(String gameName, QuestionAndAnswers survey) {
        createQuestionMenu(gameName, "Enter new question: ", survey.getQuestion(),
                String.valueOf(survey.isMultipleChoice()), survey.getChoices());
        survey.changeQuestion(scanner.nextLine());
        showThingsToUpdateAndSelect(gameName, survey);
    }

    public static void updateMultipleChoice(String gameName, QuestionAndAnswers survey) {
        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()),
                survey.getChoices());
        survey.setMultipleChoice(GlobalFunctions.createConfirmMenu("Is this a multiple choice question?"));
        showThingsToUpdateAndSelect(gameName, survey);
    }

    public static void addChoice(String gameName, QuestionAndAnswers survey) {
        createQuestionMenu(gameName, "Enter new choice: ", survey.getQuestion(),
                String.valueOf(survey.isMultipleChoice()), survey.getChoices());
        survey.addChoice(scanner.nextLine());
        showThingsToUpdateAndSelect(gameName, survey);
    }

    public static void deleteChoice(String gameName, QuestionAndAnswers survey) {
        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()),
                survey.getChoices());
        String choice = showSurveyChoicesAndSelect(survey);
        if (choice == null)
            return;

        createQuestionMenu(gameName, null, survey.getQuestion(), String.valueOf(survey.isMultipleChoice()),
                survey.getChoices());
        if (GlobalFunctions.createConfirmMenu("Are you sure you want to delete this choice '" + Colors.RESET + choice
                + Colors.CYAN_BOLD + "' from the survey?"))
            survey.getChoices().remove(choice);
        showThingsToUpdateAndSelect(gameName, survey);
    }

    public static String showSurveyChoicesAndSelect(QuestionAndAnswers survey) {
        System.out.println("├─> " + Colors.CYAN_BOLD_BRIGHT + "Select Choice to delete" + Colors.RESET);
        System.out.println("│");
        ArrayList<String> choicesList = survey.getChoices();
        for (int i = 0; i < choicesList.size(); i++) {
            System.out.println("├ <" + Colors.BLUE_BOLD + (i + 1) + Colors.RESET + "> " + choicesList.get(i));
        }

        showCancelMenu();
        if (!scanner.hasNextInt()) {
            System.out.println(GlobalFunctions.ERROR_INVALID_NUMBER);
            scanner.next();
            return showSurveyChoicesAndSelect(survey);
        }

        int selectedNumer = scanner.nextInt();
        if (selectedNumer == 0)
            return null;

        if (selectedNumer < 0 || selectedNumer > choicesList.size()) {
            System.out.println(GlobalFunctions.ERROR_INVALID_CHOICE);
            return showSurveyChoicesAndSelect(survey);
        }

        String choice = choicesList.get(selectedNumer - 1);
        scanner.nextLine();

        return choice;
    }

    public static void showCancelMenu() {
        System.out.println("│");
        System.out.println(
                "╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET + "\n");

        System.out.print(Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);
    }

    public static void answerQuestion() {
        Game selectedGame = GameController.showGamesAndSelect("Select Game");
        if (selectedGame == null)
            return;

        GlobalFunctions.clearScreen();

        String gameName = selectedGame.getName();
        ArrayList<QuestionAndAnswers> surveyList = selectedGame.getSurvey();
        if (!checkForSurveyList(surveyList, gameName))
            return;

        int surveySize = surveyList.size();
        for (int i = 0; i < surveySize; i++) {
            GlobalFunctions.clearScreen();
            QuestionAndAnswers question = surveyList.get(i);
            if (question.isMultipleChoice()) {
                showMultipleChoiceQuestionAndAnswer(question, gameName, i + 1, surveySize);
            } else {
                showQuestionAndAnswer(question, gameName, i + 1, surveySize);
            }
        }

        System.out.println(Colors.GREEN_BOLD + "\nAnswer saved successfully!" + Colors.RESET);
        GlobalFunctions.pressToContinue();
    }

    public static void showQuestionAndAnswer(QuestionAndAnswers question, String gameName, int questionNumber,
            int totalQuestions) {
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "Survey Question" + Colors.RESET);
        System.out.println("│");
        System.out.println("├ Game: " + Colors.GREEN_BOLD + gameName + Colors.RESET);
        System.out.println("├ Question [" + Colors.YELLOW_BOLD + questionNumber + Colors.RESET + "/"
                + Colors.YELLOW_BOLD + totalQuestions + Colors.RESET + "]: " + Colors.GREEN_BOLD
                + question.getQuestion() + Colors.RESET);
        System.out.println("│");
        System.out.print("╰ " + Colors.BLUE_BOLD + "Enter your answer: " + Colors.RESET);
        question.addAnswer(scanner.nextLine());
    }

    public static void showMultipleChoiceQuestionAndAnswer(QuestionAndAnswers question, String gameName,
            int questionNumber, int totalQuestions) {
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "Survey Question" + Colors.RESET);
        System.out.println("│");
        System.out.println("├ Game: " + Colors.GREEN_BOLD + gameName + Colors.RESET);
        System.out.println("├ Question [" + Colors.YELLOW_BOLD + questionNumber + Colors.RESET + " of "
                + Colors.YELLOW_BOLD + totalQuestions + Colors.RESET + "]: " + Colors.GREEN_BOLD
                + question.getQuestion() + Colors.RESET);
        System.out.println("│");
        System.out.println("├ Choices: ");
        ArrayList<String> choices = question.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            System.out.println("├ <" + Colors.BLUE_BOLD + (i + 1) + Colors.RESET + "> " + choices.get(i));
        }
        System.out.println("│");
        System.out.print("╰ " + Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);

        if (!scanner.hasNextInt()) {
            System.out.println(GlobalFunctions.ERROR_INVALID_NUMBER);
            scanner.next();
            showMultipleChoiceQuestionAndAnswer(question, gameName, questionNumber, totalQuestions);
        }

        int choice = scanner.nextInt();
        if (choice < 0 || choice > choices.size()) {
            System.out.println(GlobalFunctions.ERROR_INVALID_CHOICE);
            showMultipleChoiceQuestionAndAnswer(question, gameName, questionNumber, totalQuestions);
        }

        scanner.nextLine();
        question.addAnswer(choices.get(choice - 1));
    }
}
