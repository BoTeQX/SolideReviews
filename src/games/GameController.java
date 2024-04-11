package games;

import reviews.Review;
import reviews.ReviewController;
import utils.FileManager;

import utils.Colors;
import utils.GlobalFunctions;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Game> games = new ArrayList<>();
    public static FileManager fileManager = new FileManager();

    public static void addGame(String previousMenuTitle) {
        GlobalFunctions.clearScreen();
        System.out.println(Colors.BLUE_BOLD + "Give the game name(or leave empty to cancel): " + Colors.RESET);

        String gameName = scanner.nextLine();
        if (gameName.isEmpty()) {
            GlobalFunctions.clearScreen();
            return;
        }
        GlobalFunctions.clearScreen();
        System.out.println(Colors.BLUE_BOLD + "Give the game genre(or leave empty to cancel):" + Colors.RESET);
        String gameGenre = scanner.nextLine();
        if (gameGenre.isEmpty()) {
            GlobalFunctions.clearScreen();
            return;
        }
        GlobalFunctions.clearScreen();
        System.out.println(Colors.BLUE_BOLD + "Give the game price(or put 0 to cancel):" + Colors.RESET);
        double gamePrice = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                gamePrice = Double.parseDouble(scanner.nextLine());
                if (gamePrice <= 0) {
                    GlobalFunctions.clearScreen();
                    return;
                }
                validPrice = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input! Please enter a valid price.");
            }
        }

        String gameInfo = gameName + "~" + gameGenre + "~" + gamePrice; // formatting string

        fileManager.writeGameToFile(gameInfo); // uses the method in FileManager to write into the datafile

        Game game = new Game(gameName, gameGenre, gamePrice);
        games.add(game);
        System.out.println("Game added!");
        GameDisplayer.showSingleGame(gameName, previousMenuTitle);
    }

    public static void initiateGames() {
        ArrayList<String> gameData = fileManager.readGamesFile(); // Read game data from file
        for (String data : gameData) {
            String[] parts = data.split("~");
            String name = parts[0];
            String genre = parts[1];
            double price = Double.parseDouble(parts[2]);
            Game game = new Game(name, genre, price);
            ReviewController.initiateReviews(game);
            addToGameList(game);
        }
    }

    public static Game getGameByName(String name) {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static void addToGameList(Game game) {
        games.add(game);
    }

    public static void removeGame() {
        GlobalFunctions.clearScreen();
        Game selectedGame = showGamesAndSelect("What game do you want to remove?");
        if (selectedGame == null) {
            GlobalFunctions.clearScreen();
            System.out.println("Game not found");
            GlobalFunctions.pressToContinue();
            return;
        }
        games.remove(selectedGame); // remove the game from the arraylist
        fileManager.deleteGame(); // delete the file just like updategame
        for (Game updatedGame : games) {
            // the whole text file gets rewritten
            String gameInfo = updatedGame.getName() + "~" + updatedGame.getGenre() + "~" + updatedGame.getPrice();
            fileManager.writeGameToFile(gameInfo);
        }
        GlobalFunctions.clearScreen();
        System.out.println(selectedGame.getName() + " removed.");
        GlobalFunctions.pressToContinue();
    }

    public static void updateGame() {
        GlobalFunctions.clearScreen();
        Game selectedGame = showGamesAndSelect("What game do you want to update?");
        if (selectedGame == null) {
            GlobalFunctions.clearScreen();
            System.out.println("Game not found");
            GlobalFunctions.pressToContinue();
            return;
        }
        updateGameMenu(selectedGame);
    }

    private static void updateGameMenu(Game game) {
        GlobalFunctions.clearScreen();
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "What do you want to change?" + Colors.RESET);
        System.out.println("│");
        System.out.println("├ <" + Colors.BLUE_BOLD + (1) + Colors.RESET + "> " + "Name");
        System.out.println("├ <" + Colors.BLUE_BOLD + (2) + Colors.RESET + "> " + "Genre");
        System.out.println("├ <" + Colors.BLUE_BOLD + (3) + Colors.RESET + "> " + "Price");
        System.out.println("│");
        System.out.println(
                "╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + "Cancel" + Colors.RESET + "\n");
        int updateOpt = scanner.nextInt();

        if (updateOpt == 1) {
            GlobalFunctions.clearScreen();
            System.out.println("old name: " + game.getName());
            System.out.println(Colors.CYAN_BOLD_BRIGHT + "New name?(leave empty to cancel)" + Colors.RESET);
            scanner.nextLine();
            String newName = scanner.nextLine();
            if (newName.isEmpty()) {
                GlobalFunctions.clearScreen();
                return;
            }
            // update the name
            game.setName(newName);
        } else if (updateOpt == 2) {
            GlobalFunctions.clearScreen();
            System.out.println("old genre: " + game.getGenre());
            System.out.println(Colors.CYAN_BOLD_BRIGHT + "New genre?(leave empty to cancel)" + Colors.RESET);
            scanner.nextLine();
            String newGenre = scanner.nextLine();
            if (newGenre.isEmpty()) {
                GlobalFunctions.clearScreen();
                return;
            }
            // update the genre
            game.setGenre(newGenre);
        } else if (updateOpt == 3) {
            GlobalFunctions.clearScreen();
            System.out.println("old price: " + game.getPrice());
            System.out.println(Colors.CYAN_BOLD_BRIGHT + "New price?(put 0 to cancel)" + Colors.RESET);
            scanner.nextLine();
            double newPrice = 0;
            boolean validPrice = false;
            while (!validPrice) {
                try {
                    newPrice = Double.parseDouble(scanner.nextLine());
                    if (newPrice <= 0) {
                        GlobalFunctions.clearScreen();
                        return;
                    }
                    validPrice = true;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input! Please enter a valid price.");
                }
            }
            // update the price
            game.setPrice(newPrice);

        } else {
            GlobalFunctions.clearScreen();
            System.out.println("Update canceled.");
            return; // Exit the method if the user cancels
        }

        // Rewrite the file with the updated game data
        fileManager.deleteGame(); // Delete the existing games file
        for (Game updatedGame : games) {
            // Write each game to the file

            String gameInfo = updatedGame.getName() + "~" + updatedGame.getGenre() + "~" + updatedGame.getPrice();
            fileManager.writeGameToFile(gameInfo);
        }
        GlobalFunctions.clearScreen();
        System.out.println("Game updated.");
        GlobalFunctions.pressToContinue();
    }

    public static void addSale() {
        GlobalFunctions.clearScreen();
        Game selectedGame = showGamesAndSelect("What game do you want to put on sale?");
        if (selectedGame == null) {
            GlobalFunctions.clearScreen();
            System.out.println("Game not found");
            GlobalFunctions.pressToContinue();
            return;
        }
            System.out.println("old sale: " + selectedGame.getSale() + "%");
        System.out.println(Colors.BLUE_BOLD + "How much % sale do you want?");
        int salePercentage = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                salePercentage = Integer.parseInt(scanner.nextLine());
                if (salePercentage <= 0) {
                    GlobalFunctions.clearScreen();
                    return;
                }
                validPrice = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input! Please enter a valid price.");
            }
        }
        // int salePercentage = scanner.nextInt();
        selectedGame.setSale(salePercentage);
        GlobalFunctions.clearScreen();
        System.out.println(selectedGame.getName() + " sale updated.\nOld price: " + selectedGame.getPrice());
        System.out.printf("New price: %.2f", ((selectedGame.getPrice() / 100) * (100 - selectedGame.getSale())));
        System.out.println();
        GlobalFunctions.pressToContinue();
    }

    public static Game showGamesAndSelect(String title) {
        GlobalFunctions.clearScreen();
        System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + title + Colors.RESET);
        System.out.println("│");
        ArrayList<Game> games = getGames();
        for (int i = 0; i < games.size(); i++) {
            System.out.println("├ <" + Colors.BLUE_BOLD + (i + 1) + Colors.RESET + "> " + games.get(i).getName());
        }
        System.out.println("│");
        System.out.println(
                "╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + ">" + Colors.RED + " Cancel" + Colors.RESET + "\n");

        System.out.print(Colors.BLUE_BOLD + "Enter your choice: " + Colors.RESET);
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return showGamesAndSelect(title);
        }

        int gameIndex = scanner.nextInt();
        if (gameIndex == 0) {
            scanner.nextLine();
            return null;
        }

        if (gameIndex < 0 || gameIndex > games.size()) {
            System.out.println("Invalid choice. Please enter a valid option.");
            return showGamesAndSelect(title);
        }

        Game selectedGame = games.get(gameIndex - 1);
        scanner.nextLine();

        return selectedGame;
    }
}
