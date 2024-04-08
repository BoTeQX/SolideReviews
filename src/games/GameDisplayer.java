package games;

import submenus.admin.ManageGameCatalogSubmenu;
import submenus.games.GamesCatalogSubmenu;

import utils.Colors;
import utils.GlobalFunctions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class GameDisplayer extends GameController {

    // Method to display the top bar of the table
    protected static void displayTopBar() {
        System.out.println(
                "+------------------------------------------------------+--------------------------------+--------------+");
        System.out.println(
                "| Title                                                | Genre                          | Price        |");
        System.out.println(
                "+------------------------------------------------------+--------------------------------+--------------+");
    }

    // Method to display a game from the catalog in the table format
    protected static void displayGame(Game game) {
        displayTopBar();
        System.out.printf("| %-52s | %-30s | %s$%-10.2f%s  |\n", game.getName(), game.getGenre(), Colors.GREEN_BOLD, game.getPrice(), Colors.RESET);
        displayBottomBar();
    }

    // Method to display the bottom bar of the table
    protected static void displayBottomBar() {
        System.out.println(
                "+------------------------------------------------------+--------------------------------+--------------+\n");
    }

    // Method to display a message to pause the screen and wait for the user to press enter
    protected static void displayPauseMessage(String previousMenuTitle) {
        System.out.println();
        GlobalFunctions.pressToContinue(); // Call the pressToContinue method to display the message and wait for the user to press enter
        GlobalFunctions.clearScreen(); // Check if the user came from the AdminMenu or the GamesCatalogSubmenu

        // Check if the user came from the AdminMenu or the GamesCatalogSubmenu
        if (previousMenuTitle.equals("ADMIN MENU > Manage game catalog")) {
            new ManageGameCatalogSubmenu().initiateMenu(); // If the user came from the AdminMenu, redirect the user to the ManageGameCatalogSubmenu
        } else {
            new GamesCatalogSubmenu().initiateMenu(); // If the user came from the GamesCatalogSubmenu, redirect the user to the GamesCatalogSubmenu
        }
    }

    // Method to display all games in the catalog
    public static void showAllGames(String previousMenuTitle) {
        GlobalFunctions.clearScreen(); // Clear the screen
        displayTopBar();  // Call the displayTopBar method to display the top bar of the table

        // Loop through the games ArrayList and display each game in the catalog
        for (Game game : games) {
            displayGame(game); // Call the displayGame method to display the game in the table format
        }
        displayBottomBar(); // Call the displayBottomBar method to display the bottom bar of the table
        System.out.println("Total games: " + Colors.PURPLE + games.size() + Colors.RESET); // Display the total number of games in the catalog
        displayPauseMessage(previousMenuTitle); // Call the displayPauseMessage method to display a message to pause the screen and wait for the user to press enter
    }

    // Method to display all genres in the catalog
    private static void getAllGenres() {
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "AVAILABLE GENRES: " + Colors.RESET);
        System.out.println("│");

        ArrayList<String> uniqueGenres = new ArrayList<>(); // Create an ArrayList to store the unique genres since a genre can be repeated in the catalog

        // Loop through the games ArrayList and add each unique genre to the uniqueGenres ArrayList
        for (Game game : games) {
            String genre = game.getGenre();
            // Check if the genre is not already in the uniqueGenres ArrayList and add it if it is not
            if (!uniqueGenres.contains(genre)) {
                uniqueGenres.add(genre); // Add the genre to the uniqueGenres ArrayList
            }
        }

        int index = 1; // Initialize the index to 1 for the first genre to display as the first option in the menu

        // Loop through the uniqueGenres ArrayList and display each genre with an index
        for (String genre : uniqueGenres) {
            System.out.println("├ <" + Colors.BLUE_BOLD + index++ + Colors.RESET + "> " + genre); // Display the genre with an index
        }
    }


    public static void chooseGenreAndShowGames(String previousMenuTitle) {
        GlobalFunctions.clearScreen(); // Clear the screen
        getAllGenres(); // Call the getAllGenres method to display all genres in the catalog

        System.out.println("│");
        System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET); // Display the option to cancel
        System.out.println();
        System.out.println(Colors.BLUE_BOLD + "Choose a genre: " + Colors.RESET);
        int choice = scanner.nextInt(); // Get the user's choice for the genre

        // Check if the user's choice is valid and display the games for the selected genre (more than 0 because 0 is to cancel and less than or equal to the number of genres)
        if (choice > 0 && choice <= games.size()) {
            String selectedGenre = games.get(choice - 1).getGenre(); // Get the selected genre based on the user's choice (subtract 1 because the index starts at 0)
            GlobalFunctions.clearScreen(); // Clear the screen
            showGamesByGenre(selectedGenre, previousMenuTitle); // Call the showGamesByGenre method to display the games for the selected genre and pass the previousMenuTitle to know where the user came from
        }
        // Check if the user's choice is (0) to cancel
        else if (choice == 0) {
            new GamesCatalogSubmenu().initiateMenu(); //redirect the user to the GamesCatalogSubmenu
        }
        // If the user's choice is invalid
        else {
            chooseGenreAndShowGames(previousMenuTitle); // Call the chooseGenreAndShowGames method to display the genres again and ask the user to choose a valid genre
        }
    }

    // Method to display the games for the selected genre
    private static void showGamesByGenre(String genre, String previousMenuTitle) {
        ArrayList<Game> gamesByGenre = new ArrayList<>(); // Create an ArrayList to store the games for the selected genre

        // Loop through the games ArrayList and add the games for the selected genre to the gamesByGenre ArrayList
        for (Game game : games) {
            // Check if the genre of the game of the game is the same as the selected genre
            if (game.getGenre().equals(genre)) {
                gamesByGenre.add(game); // Add the game to the gamesByGenre ArrayList if the genre is the same
            }
        }
        if (gamesByGenre.isEmpty()) {
            System.out.println(Colors.RED + "No games found for the specified genre." + Colors.RESET);
        } else {
            displayTopBar(); // Call the displayTopBar method to display the top bar of the table
            for (Game game : gamesByGenre) {
                displayGame(game); // Call the displayGame method to display the game in the table format
            }
            displayBottomBar(); // Call the displayBottomBar method to display the bottom bar of the table

            System.out.println("Total games: " + Colors.PURPLE + gamesByGenre.size() + Colors.RESET);
            displayPauseMessage(previousMenuTitle); // Call the displayPauseMessage method to display a message to pause the screen and wait for the user to press enter (passes the previousMenuTitle to know where the user came from)
        }
    }

    // Method to display a single game from the catalog in the table format
    public static void showSingleGame(String gameName, String previousMenuTitle) {
        GlobalFunctions.clearScreen(); // Clear the screen

        // Loop through the games ArrayList and display the game with the specified name
        for (Game game : games) {
            // Check if the name of the game is the same as the specified name
            if (game.getName().equals(gameName)) {
                displayTopBar(); // Call the displayTopBar method to display the top bar of the table
                displayGame(game); // Call the displayGame method to display the game in the table format
                displayBottomBar(); // Call the displayBottomBar method to display the bottom bar of the table
                displayPauseMessage(previousMenuTitle); // Call the displayPauseMessage method to display a message to pause the screen and wait for the user to press enter (passes the previousMenuTitle to know where the user came from)
            }
        }
    }
}

