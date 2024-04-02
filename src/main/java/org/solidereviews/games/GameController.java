package org.solidereviews.games;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.games.GamesCatalogSubmenu;
import org.solidereviews.utils.Colors;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Game> games = new ArrayList<>();

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
        GameDisplayer.showAllGames();
    }

    public static void initiateGames() {
        // this is temporary until the "database" is implemented
        Game game1 = new Game("Game 1", "Shooter", 19.99);
        Game game2 = new Game("Game 2", "RPG", 14.99);
        Game game3 = new Game("Game 3", "Survival", 29.99);
        addToGameList(game1);
        addToGameList(game2);
        addToGameList(game3);
    }

    public static void addToGameList(Game game) {
        games.add(game);
    }

    public static void removeGame() {

    }

    public static void updateGame() {

    }

    public static void addSale() {

    }

    public static ArrayList<Game> getAllGames() {
        return games;
    }

}
