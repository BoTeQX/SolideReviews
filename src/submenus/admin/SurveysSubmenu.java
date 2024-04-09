package submenus.admin;

import interfaces.Menu;
import menus.AdminMenu;

import utils.Colors;
import utils.GlobalFunctions;

import surveys.SurveyController;

public class SurveysSubmenu implements Menu {

    String title = "ADMIN MENU > Surveys";
    String[] menuItems = { "Create Survey", "Update survey", "Delete survey", "Show survey result(s)",
            Colors.RED_BACKGROUND_BRIGHT + "[DEBUG]" + Colors.RESET + " Answer question" };

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
            case 1 -> SurveyController.createSurvey();
            case 2 -> SurveyController.updateSurvey();
            case 3 -> SurveyController.deleteSurvey();
            case 4 -> SurveyController.showSurveyResult();
            case 5 -> SurveyController.answerQuestion();
            case 9 -> new AdminMenu().initiateMenu();
            case 0 -> GlobalFunctions.closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}
