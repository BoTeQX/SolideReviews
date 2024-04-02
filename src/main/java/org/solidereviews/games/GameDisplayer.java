package org.solidereviews.games;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.admin.ManageGameCatalogSubmenu;
import org.solidereviews.submenus.games.GamesCatalogSubmenu;
import org.solidereviews.utils.Colors;

import java.util.ArrayList;
import java.util.Scanner;

public class GameDisplayer extends GameController{
    public static void showAllGames(String previousMenuTitle){
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
        if (previousMenuTitle.equals("ADMIN MENU > Manage game catalog")) {
            new ManageGameCatalogSubmenu().initiateMenu();
        }
        else {
            new GamesCatalogSubmenu().initiateMenu();
        }
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
            new GamesCatalogSubmenu().initiateMenu();
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
            new GamesCatalogSubmenu().initiateMenu();
        }
    }


}
