package submenus.games;

import interfaces.Menu;
import menus.GamesMenu;

import ranking.RankingDisplayer;
import utils.Colors;
import utils.GlobalFunctions;

public class GameRankingsSubMenu implements Menu {

    String title = "GAMES MENU > Game rankings";
    String[] menuItems = { "Show all rankings" };

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
            case 1 -> RankingDisplayer.displayFullRanking();
            case 9 -> new GamesMenu().initiateMenu();
            case 0 -> GlobalFunctions.closeProgram();
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}
