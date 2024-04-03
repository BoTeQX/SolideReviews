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
<<<<<<< Updated upstream
=======

        String gameInfo = gameName + "~" + gameGenre + "~" + gamePrice; //formatting string

        FileManager fileManager = new FileManager();
        fileManager.writeGameToFile(gameInfo); //uses the method in FileManager to write into the datafile

>>>>>>> Stashed changes
        Game game = new Game(gameName, gameGenre, gamePrice);
        games.add(game);
        System.out.println("Game added!");
        GameDisplayer.showSingleGame(gameName, previousMenuTitle);
    }

<<<<<<< Updated upstream
    public static void initiateGames(){
        //this is temporary until the "database" is implemented
        Game game1 = new Game("Game 1", "Shooter", 19.99);
        Game game2 = new Game("Game 2", "RPG", 14.99);
        Game game3 = new Game("Game 3", "Survival", 29.99);
        addToGameList(game1);
        addToGameList(game2);
        addToGameList(game3);
=======
    public static void initiateGames() {
        FileManager fileManager = new FileManager();
        ArrayList<String> gameData = fileManager.readGamesFile(); // Read game data from file
        for (String data : gameData) {
            String[] parts = data.split("~");
            String name = parts[0];
            String genre = parts[1];
            double price = Double.parseDouble(parts[2]);
            Game game = new Game(name, genre, price);
            addToGameList(game);
        }
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
            if(game.getName().equalsIgnoreCase(gameName)){
                games.remove(game);
=======
            if (game.getName().equalsIgnoreCase(gameName)) {
                games.remove(game); // remove the game from the arraylist

                // Rewrite the file without the removed game
                FileManager fileManager = new FileManager();
                fileManager.deleteGamesFile(); // delete the file just like updategame
                for (Game updatedGame : games) {
                    // the whole text file gets rewritten
                    String gameInfo = updatedGame.getName() + "~" + updatedGame.getGenre() + "~" + updatedGame.getPrice();
                    fileManager.writeGameToFile(gameInfo);
                }
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
                    updateGameMenu(game.getName());
                }else {
                    break;
=======
                // Rewrite the file with the updated game data
                FileManager fileManager = new FileManager();
                fileManager.deleteGamesFile(); // Delete the existing games file
                for (Game updatedGame : games) {
                    // Write each game to the file
                    String gameInfo = updatedGame.getName() + "~" + updatedGame.getGenre() + "~" + updatedGame.getPrice();
                    fileManager.writeGameToFile(gameInfo);
>>>>>>> Stashed changes
                }
                System.out.println(game.getName() + " updated.");
                break;
            }
        }
<<<<<<< Updated upstream
=======

        // If the game with the specified name is not found
        System.out.println("Game not found.");
>>>>>>> Stashed changes
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
