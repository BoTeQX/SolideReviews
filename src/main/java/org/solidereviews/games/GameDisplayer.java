package org.solidereviews.games;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.admin.ManageGameCatalogSubmenu;
import org.solidereviews.submenus.games.GamesCatalogSubmenu;

import org.solidereviews.utils.Colors;
import org.solidereviews.utils.GlobalFunctions;

import java.util.ArrayList;
import java.util.Scanner;

public class GameDisplayer extends GameController {

    protected static void displayTopBar() {
        System.out.println("+------------------------------------------------------+--------------------------------+--------------+");
        System.out.println("| Title                                                | Genre                          | Price        |");
        System.out.println("+------------------------------------------------------+--------------------------------+--------------+");
    }

    protected static void displayBottomBar() {
        System.out.println("+------------------------------------------------------+--------------------------------+--------------+\n");
    }

    protected static void displayPauseMessage(String previousMenuTitle) {
        System.out.println();
        GlobalFunctions.pressToContinue();
        GlobalFunctions.clearScreen();
        if (previousMenuTitle.equals("ADMIN MENU > Manage game catalog")) {
            new ManageGameCatalogSubmenu().initiateMenu();
        } else {
            new GamesCatalogSubmenu().initiateMenu();
        }
    }

    public static void showAllGames(String previousMenuTitle) {
        GlobalFunctions.clearScreen();
        displayTopBar();
        for (Game game : games) {
            System.out.printf("| %-52s | %-30s | %s$%-10.2f%s  |\n", game.getName(), game.getGenre(), Colors.GREEN_BOLD, game.getPrice(), Colors.RESET);
        }
        displayBottomBar();
        System.out.println("Total games: " + Colors.PURPLE + games.size() + Colors.RESET);
        displayPauseMessage(previousMenuTitle);

    }


    private static void getAllGenres() {
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "AVAILABLE GENRES: " + Colors.RESET);
        System.out.println("│");

        ArrayList<String> uniqueGenres = new ArrayList<>();
        for (Game game : games) {
            String genre = game.getGenre();
            if (!uniqueGenres.contains(genre)) {
                uniqueGenres.add(genre);
            }
        }

        int index = 1;
        for (String genre : uniqueGenres) {
            System.out.println("├ <" + Colors.BLUE_BOLD + index++ + Colors.RESET + "> " + genre);
        }
    }

    public static void chooseGenreAndShowGames(String previousMenuTitle) {
        GlobalFunctions.clearScreen();
        getAllGenres();
        System.out.println("│");
        System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET);
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println(Colors.BLUE_BOLD + "Choose a genre: " + Colors.RESET);
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= games.size()) {
            String selectedGenre = games.get(choice - 1).getGenre();
            GlobalFunctions.clearScreen();
            showGamesByGenre(selectedGenre, previousMenuTitle);
        } else if (choice == 0) {
            new GamesCatalogSubmenu().initiateMenu();
        } else {
            chooseGenreAndShowGames(previousMenuTitle);
        }
    }

    private static void showGamesByGenre(String genre, String previousMenuTitle) {
        ArrayList<Game> gamesByGenre = new ArrayList<>();
        for (Game game : games) {
            if (game.getGenre().equals(genre)) {
                gamesByGenre.add(game);
            }
        }
        if (gamesByGenre.isEmpty()) {
            System.out.println(Colors.RED + "No games found for the specified genre." + Colors.RESET);
        } else {
            displayTopBar();
            for (Game game : gamesByGenre) {
                System.out.printf("| %-52s | %-30s | %s$%-10.2f%s  |\n", game.getName(), game.getGenre(), Colors.GREEN_BOLD, game.getPrice(), Colors.RESET);
            }
            displayBottomBar();

            System.out.println("Total games: " + Colors.PURPLE + gamesByGenre.size() + Colors.RESET);
            displayPauseMessage(previousMenuTitle);

            new GamesCatalogSubmenu().initiateMenu();
        }
    }


    public static void showSingleGame(String gameName, String previousMenuTitle) {
        GlobalFunctions.clearScreen();
        for (Game game : games) {
            if (game.getName().equals(gameName)) {
                displayTopBar();
                System.out.printf("| %-52s | %-30s | %s$%-10.2f%s  |\n", game.getName(), game.getGenre(), Colors.GREEN_BOLD, game.getPrice(), Colors.RESET);
                displayBottomBar();
                displayPauseMessage(previousMenuTitle);
            }
        }
    }
}
