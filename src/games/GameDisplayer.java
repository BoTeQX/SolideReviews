package games;

import submenus.admin.ManageGameCatalogSubmenu;
import submenus.games.GamesCatalogSubmenu;
import utils.Colors;
import utils.GlobalFunctions;
import java.util.ArrayList;

public class GameDisplayer extends GameController {

    protected static void displayTopBar() {
        System.out.println("+------------------------------------------------------+--------------------------------+--------------+");
        System.out.println("| Title                                                | Genre                          | Price        |");
        System.out.println("+------------------------------------------------------+--------------------------------+--------------+");
    }

    public static void displayGame(Game game) {
        System.out.printf("| %-52s | %-30s | %s$%-10.2f%s  |\n", game.getName(), game.getGenre(), Colors.GREEN_BOLD, game.getPrice(), Colors.RESET);
    }

    protected static void displayBottomBar() {
        System.out.println("+------------------------------------------------------+--------------------------------+--------------+\n");
    }

    protected static void displayPauseMessage(String previousMenuTitle) {
        System.out.println();
        GlobalFunctions.pressToContinue();
        GlobalFunctions.clearScreen();

        // Check if the user came from the AdminMenu or the GamesCatalogSubmenu
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
            displayGame(game);
        }
        displayBottomBar();
        System.out.println("Total games: " + Colors.PURPLE + games.size() + Colors.RESET);
        displayPauseMessage(previousMenuTitle);
    }

    private static void getAllGenres() {
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "AVAILABLE GENRES: " + Colors.RESET);
        System.out.println("│");
        ArrayList<String> uniqueGenres = getUniqueGenres();

        int index = 1;

        for (String genre : uniqueGenres) {
            System.out.println("├ <" + Colors.BLUE_BOLD + index++ + Colors.RESET + "> " + genre); // Display the genre with an index
        }
    }

    private static ArrayList<String> getUniqueGenres() {
        ArrayList<String> uniqueGenres = new ArrayList<>();

        for (Game game : games) {
            String genre = game.getGenre();
            if (!uniqueGenres.contains(genre)) {
                uniqueGenres.add(genre);
            }
        }
        return uniqueGenres;
    }


    public static void chooseGenreAndShowGames(String previousMenuTitle) {
        GlobalFunctions.clearScreen();
        getAllGenres();

        System.out.println("│");
        System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET);
        System.out.println();
        System.out.println(Colors.BLUE_BOLD + "Choose a genre: " + Colors.RESET);
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= games.size()) {
            String selectedGenre = games.get(choice - 1).getGenre();
            GlobalFunctions.clearScreen();
            showGamesByGenre(selectedGenre, previousMenuTitle); // pass the previousMenuTitle to know where the user came from
        }
        // Check if the user's choice is (0) to cancel
        else if (choice == 0) {
            new GamesCatalogSubmenu().initiateMenu();
        }
        else {
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
                displayGame(game);
            }
            displayBottomBar();

            System.out.println("Total games: " + Colors.PURPLE + gamesByGenre.size() + Colors.RESET);
            displayPauseMessage(previousMenuTitle); // passes the previousMenuTitle to know where the user came from
        }
    }

    public static void showSingleGame(String gameName, String previousMenuTitle) {
        GlobalFunctions.clearScreen();

        for (Game game : games) {
            if (game.getName().equals(gameName)) {
                displayTopBar();
                displayGame(game);
                displayBottomBar();
                displayPauseMessage(previousMenuTitle); // passes the previousMenuTitle to know where the user came from
            }
        }
    }
}

