package org.solidereviews.games;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.submenus.games.GamesCatalogSubmenu;
import org.solidereviews.utils.Colors;
import org.solidereviews.utils.FileManager;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Game> games = new ArrayList<>();

    public static void addGame(String previousMenuTitle) {
        clearScreen();
        System.out.println(Colors.BLUE_BOLD + "Give the game name(or leave empty to cancel): " + Colors.RESET);

        String gameName = scanner.nextLine();
        if (gameName.isEmpty()) {
            clearScreen();
            return;
        }
        clearScreen();
        System.out.println(Colors.BLUE_BOLD + "Give the game genre(or leave empty to cancel):" + Colors.RESET);
        String gameGenre = scanner.nextLine();
        if (gameGenre.isEmpty()) {
            clearScreen();
            return;
        }
        clearScreen();
        System.out.println(Colors.BLUE_BOLD + "Give the game price(or put 0 to cancel):" + Colors.RESET);
        double gamePrice = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                gamePrice = Double.parseDouble(scanner.nextLine());
                if (gamePrice <= 0) {
                    clearScreen();
                    return;
                }
                validPrice = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input! Please enter a valid price.");
            }
        }

        String gameInfo = gameName + "~" + gameGenre + "~" + gamePrice; // formatting string

        FileManager fileManager = new FileManager();
        fileManager.writeGameToFile(gameInfo); // uses the method in FileManager to write into the datafile

        Game game = new Game(gameName, gameGenre, gamePrice);
        games.add(game);
        System.out.println("Game added!");
        GameDisplayer.showSingleGame(gameName, previousMenuTitle);
    }

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
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static void addToGameList(Game game) {
        games.add(game);
    }

    public static void removeGame() {
        clearScreen();
        Game selectedGame = showGamesAndSelect("What game do you want to remove?");
        if (selectedGame == null) {
            clearScreen();
            System.out.println("Game not found");
            pressToContinue();
            return;
        }
        games.remove(selectedGame); // remove the game from the arraylist
        FileManager fileManager = new FileManager();
        fileManager.deleteGamesFile(); // delete the file just like updategame
        for (Game updatedGame : games) {
            // the whole text file gets rewritten
            String gameInfo = updatedGame.getName() + "~" + updatedGame.getGenre() + "~" + updatedGame.getPrice();
            fileManager.writeGameToFile(gameInfo);
        }
        clearScreen();
        System.out.println(selectedGame.getName() + " removed.");
        pressToContinue();
    }

    public static void updateGame() {
        clearScreen();
        Game selectedGame = showGamesAndSelect("What game do you want to update?");
        if (selectedGame == null) {
            clearScreen();
            System.out.println("Game not found");
            pressToContinue();
            return;
        }
        updateGameMenu(selectedGame);
    }

    private static void updateGameMenu(Game game) {
        clearScreen();
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
            clearScreen();
            System.out.println(Colors.CYAN_BOLD_BRIGHT + "New name?(leave empty to cancel)" + Colors.RESET);
            scanner.nextLine();
            String newName = scanner.nextLine();
            if (newName.isEmpty()) {
                clearScreen();
                return;
            }
            // update the name
            game.setName(newName);
        } else if (updateOpt == 2) {
            clearScreen();
            System.out.println(Colors.CYAN_BOLD_BRIGHT + "New genre?(leave empty to cancel)" + Colors.RESET);
            scanner.nextLine();
            String newGenre = scanner.nextLine();
            if (newGenre.isEmpty()) {
                clearScreen();
                return;
            }
            // update the genre
            game.setGenre(newGenre);
        } else if (updateOpt == 3) {
            clearScreen();
            System.out.println(Colors.CYAN_BOLD_BRIGHT + "New price?(put 0 to cancel)" + Colors.RESET);
            scanner.nextLine();
            double newPrice = 0;
            boolean validPrice = false;
            while (!validPrice) {
                try {
                    newPrice = Double.parseDouble(scanner.nextLine());
                    if (newPrice <= 0) {
                        clearScreen();
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
            clearScreen();
            System.out.println("Update canceled.");
            return; // Exit the method if the user cancels
        }

        // Rewrite the file with the updated game data
        FileManager fileManager = new FileManager();
        fileManager.deleteGamesFile(); // Delete the existing games file
        for (Game updatedGame : games) {
            // Write each game to the file

            String gameInfo = updatedGame.getName() + "~" + updatedGame.getGenre() + "~" + updatedGame.getPrice();
            fileManager.writeGameToFile(gameInfo);
        }
        clearScreen();
        System.out.println("Game updated.");
        pressToContinue();
    }

    public static void addSale() {
        clearScreen();
        Game selectedGame = showGamesAndSelect("What game do you want to put on sale?");
        if (selectedGame == null) {
            clearScreen();
            System.out.println("Game not found");
            pressToContinue();
            return;
        }
        System.out.println(Colors.BLUE_BOLD + "How much % sale do you want?");
        int salePercentage = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                salePercentage = Integer.parseInt(scanner.nextLine());
                if (salePercentage <= 0) {
                    clearScreen();
                    return;
                }
                validPrice = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input! Please enter a valid price.");
            }
        }
        // int salePercentage = scanner.nextInt();
        selectedGame.setSale(salePercentage);
        clearScreen();
        System.out.println(selectedGame.getName() + " sale updated.\nOld price: " + selectedGame.getPrice());
        System.out.printf("New price: %.2f", ((selectedGame.getPrice() / 100) * (100 - selectedGame.getSale())));
        System.out.println();
        pressToContinue();
    }

    public static Game showGamesAndSelect(String title) {
        clearScreen();
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

    // public static ArrayList<Game> getGames() {
    // return games;
    // }

    protected static void clearScreen() {
        System.out.print("\033\143");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected static void pressToContinue() {
        System.out.println(Colors.BLUE_BOLD + "\nPress Enter to continue..." + Colors.RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        clearScreen();
    }
}
