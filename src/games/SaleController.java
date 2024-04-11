package games;

import utils.Colors;
import utils.GlobalFunctions;

public class SaleController extends GameController {

    protected static void displayTopBar() {
        System.out.println("+----------------------------------------------+--------------------------------+--------------+----------------+");
        System.out.println("| Title                                        | Genre                          | Percentage   | New Price      |");
        System.out.println("+----------------------------------------------+--------------------------------+--------------+----------------+");
    }

    public static void displayGame(Game game) {
        System.out.printf("| %-44s | %-30s | %-12s | %s$%-5.2f%s %s$%-5.2f%s  |\n", game.getName(), game.getGenre(), game.getSale() + "%",Colors.RED + "\u001B[9m", game.getPrice(), Colors.RESET, Colors.GREEN_BOLD, (game.getPrice() * ((double)(100 - game.getSale())/ 100)), Colors.RESET);
    }

    protected static void displayBottomBar() {
        System.out.println("+----------------------------------------------+--------------------------------+--------------+----------------+\n");
    }
    public static void showGamesOnSale(){
        GlobalFunctions.clearScreen();
        displayTopBar();

        int gamesOnSaleCounter = 0;

        for (Game game : games) {
            if (game.getSale() > 0) {
                displayGame(game);
                gamesOnSaleCounter += 1;
            }
        }
        displayBottomBar();
        System.out.println("Total games on sale: " + Colors.PURPLE + gamesOnSaleCounter + Colors.RESET);
        System.out.println();
        GlobalFunctions.pressToContinue();
        GlobalFunctions.clearScreen();
    }

}
