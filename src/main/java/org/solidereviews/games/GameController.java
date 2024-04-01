package org.solidereviews.games;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.games.GamesCatalogSubmenu;
import org.solidereviews.utils.Colors;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Game> games = new ArrayList<>();

    public static void addGame() {
        System.out.println("Give the game name:");
        String gameName = scanner.nextLine();
        System.out.println("Give the game genre:");
        String gameGenre = scanner.nextLine();
        System.out.println("Give the game price:");
        double gamePrice = scanner.nextDouble();
        Game game = new Game(gameName, gameGenre, gamePrice);
        games.add(game);
        System.out.println("Game added!");
        showAllGames();
    }

    public static void initiateGames(){
        //this is temporary until the "database" is implemented
        Game game1 = new Game("Game 1", "Shooter", 19.99);
        Game game2 = new Game("Game 2", "RPG", 14.99);
        Game game3 = new Game("Game 3", "Survival", 29.99);
        addToGameList(game1);
        addToGameList(game2);
        addToGameList(game3);
    }

    public static void showAllGames(){
        clearScreen();
        System.out.println("+----------------------------------+------------+-------+");
        System.out.println("| Title                            | Genre      | Price |");
        System.out.println("+----------------------------------+------------+-------+");

        for (Game game : games) {
            System.out.printf("| %-32s | %-10s | %s$%-5.2f%s |\n", game.getName(), game.getGenre(), Colors.GREEN_BOLD, game.getPrice(), Colors.RESET);
        }

        System.out.println("+----------------------------------+------------+-------+");
        System.out.println();
        System.out.println("Total games: " + Colors.PURPLE + games.size() + Colors.RESET);
        System.out.println();
        System.out.println(Colors.BLUE_BOLD + "Press Enter to continue..." + Colors.RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        clearScreen();
        Menu menu = new GamesCatalogSubmenu();
        menu.initiateMenu();
    }


    private static void getAllGenres(){
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "AVAILABLE GENRES: " + Colors.RESET);
        System.out.println("│");
        for (int i = 0; i < games.size(); i++) {
            System.out.println("├ <" + Colors.BLUE_BOLD + (i+1) + Colors.RESET + "> " + games.get(i).getGenre());
        }
    }

    public static void chooseGenreAndShowGames(){
        clearScreen();
        getAllGenres();
        System.out.println("│");
        System.out.println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET);
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println(Colors.BLUE_BOLD + "Choose a genre: " + Colors.RESET);
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= games.size()) {
            String selectedGenre = games.get(choice - 1).getGenre();
            clearScreen();
            showGamesByGenre(selectedGenre);
        } else if (choice == 0) {
            Menu menu = new GamesCatalogSubmenu();
            menu.initiateMenu();
        } else {
            chooseGenreAndShowGames();
        }
    }

    private static void showGamesByGenre(String genre){
        ArrayList<Game> gamesByGenre = new ArrayList<>();
        for (Game game : games) {
            if (game.getGenre().equals(genre)) {
                gamesByGenre.add(game);
            }
        }
        if (gamesByGenre.isEmpty()) {
            System.out.println(Colors.RED + "No games found for the specified genre." + Colors.RESET);
        } else {
            System.out.println("+----------------------------------+------------+-------+");
            System.out.println("| Title                            | Genre      | Price |");
            System.out.println("+----------------------------------+------------+-------+");

            for (Game game : gamesByGenre) {
                System.out.printf("| %-32s | %-10s | %s$%-5.2f%s |\n", game.getName(), game.getGenre(), Colors.GREEN_BOLD, game.getPrice(), Colors.RESET);
            }

            System.out.println("+----------------------------------+------------+-------+");
            System.out.println();
            System.out.println("Total games: " + Colors.PURPLE +  gamesByGenre.size() + Colors.RESET);
            System.out.println();
            System.out.println(Colors.BLUE_BOLD + "Press Enter to continue..." + Colors.RESET);
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            Menu menu = new GamesCatalogSubmenu();
            menu.initiateMenu();
        }
    }

    private static void clearScreen() {
            System.out.print("\033\143");
            System.out.print("\033[H\033[2J");
            System.out.flush();
    }


    public static void addToGameList(Game game){
        games.add(game);
    }

    public static void removeGame(){

    }

    public static void updateGame(){

    }

    public static void addSale(){

    }
}
