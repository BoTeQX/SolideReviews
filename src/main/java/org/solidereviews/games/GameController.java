package org.solidereviews.games;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Game> games = new ArrayList<>();

    public void addGame() {
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
    public void showAllGames(){
        for(Game item : games){
            System.out.println(item.getName());
            System.out.println(item.getGenre());
            System.out.println(item.getPrice());
        }
    }
    public void addToGameList(Game game){
        games.add(game);
    }

    public void removeGame(){

    }

    public void updateGame(){

    }

    public void addSale(){

    }
}
