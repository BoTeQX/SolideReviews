package org.solidereviews.games;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.games.GamesCatalogSubmenu;
import org.solidereviews.utils.Colors;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Game> games = new ArrayList<>();


    public static void addGame(String previousMenuTitle) {
        clearScreen();
        System.out.println("Give the game name(or leave empty to cancel): ");

        String gameName = scanner.nextLine();
        if (gameName.isEmpty()){
            return;
        }
        System.out.println("Give the game genre(or leave empty to cancel):");
        String gameGenre = scanner.nextLine();
        if (gameGenre.isEmpty()){
            return;
        }
        System.out.println("Give the game price(or put 0 to cancel):");
        double gamePrice = scanner.nextDouble();
        if (gamePrice <= 0){
            return;
        }
        Game game = new Game(gameName, gameGenre, gamePrice);
        games.add(game);
        System.out.println("Game added!");
        GameDisplayer.showSingleGame(gameName, previousMenuTitle);
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


    public static void addToGameList(Game game){
        games.add(game);
    }

    public static void removeGame(){
        clearScreen();
        System.out.println("What game do you want to remove?(or leave empty to cancel)");
        String gameName = scanner.nextLine();
        if (gameName.isEmpty()){
            return;
        }
        for (Game game : games) {
            if(game.getName().equalsIgnoreCase(gameName)){
                games.remove(game);
                System.out.println(game.getName() + " removed.");
                break;
            }
        }

    }

    public static void updateGame(){
        clearScreen();
        System.out.println("What game do you want to update?(or leave empty to cancel)");
        String gameName = scanner.nextLine();
        if (gameName.isEmpty()){
            return;
        }
        updateGameMenu(gameName);
    }
    private static void updateGameMenu(String gameName){
        for (Game game : games) {
            if(game.getName().equalsIgnoreCase(gameName)){
                System.out.println("What do you want to change?\n1. Name\n2. Genre\n3. Price\n4. Cancel");
                int updateOpt = scanner.nextInt();
                if(updateOpt == 1) {
                    System.out.println("New name?");
                    scanner.nextLine();
                    String newName = scanner.nextLine();
                    game.setName(newName);

                    updateGameMenu(newName);
                }else if (updateOpt == 2){
                    System.out.println("New genre?");
                    scanner.nextLine();
                    String newGenre = scanner.nextLine();
                    game.setGenre(newGenre);

                    updateGameMenu(game.getName());
                }else if(updateOpt == 3){
                    System.out.println("New price?");
                    scanner.nextLine();
                    double newPrice = scanner.nextDouble();
                    game.setPrice(newPrice);

                    updateGameMenu(game.getName());
                }else {
                    break;
                }
                System.out.println(game.getName() + " updated.");
                break;
            }
        }
    }

    public static void addSale(){
        clearScreen();
        System.out.println("What game do you want to put on sale?(or leave empty to cancel)");
        String gameName = scanner.nextLine();
        if (gameName.isEmpty()){
            return;
        }
        for (Game game : games) {
            if(game.getName().equalsIgnoreCase(gameName)){
                System.out.println("How much % sale do you want?");
                int salePercentage = scanner.nextInt();
                game.setSale(salePercentage);
                System.out.println(game.getName() + " sale updated.\nOld price: " + game.getPrice());
                System.out.printf("New price: %.2f", ((game.getPrice() / 100) * (100 - game.getSale())));
                System.out.println();
                break;
            }
        }
    }
    protected static void clearScreen() {
        System.out.print("\033\143");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
