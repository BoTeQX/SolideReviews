
import java.util.Scanner;
import java.util.ArrayList;

public class GameMenu {

    private final Scanner scanner;

    public GameMenu() {
        scanner = new Scanner(System.in);
    }

    public void displayGameMenu() {
        int choice = -1;

        do {
            System.out.println("╭──> " + Colors.CYAN_BOLD_BRIGHT + "GAMES " + Colors.RESET);
            System.out.println("│");
            System.out.println("├ <" + Colors.BLUE_BOLD + "1" + Colors.RESET + "> Show all games");
            System.out.println("├ <" + Colors.BLUE_BOLD + "2" + Colors.RESET + "> Show games by genre");
            System.out.println("│");
            System.out.println("├ <" + Colors.BLUE_BOLD + "9" + Colors.RESET + "> Back to Main Menu");
            System.out
                    .println("╰ <" + Colors.BLUE_BOLD + "0" + Colors.RESET + "> " + Colors.RED + "Exit" + Colors.RESET);
            System.out.println();

            System.out.print(Colors.PURPLE + "Enter your choice: " + Colors.RESET);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
            }

            switch (choice) {
                case 1 -> displayAllGames();
                case 2 -> displayGamesByGenre();
                case 9 -> {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.displayMainMenu();
                }
                case 0 -> {
                    System.out.println("Exiting the program. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private void displayAllGames() {
        GameInitializer gameInitializer = new GameInitializer();
        gameInitializer.initializeGames();
        ArrayList<Game> games = gameInitializer.getGames();

        System.out.println("+----------------------------------+------------+-------+");
        System.out.println("| Title                            | Genre      | Price |");
        System.out.println("+----------------------------------+------------+-------+");

        for (Game game : games) {
            System.out.printf("| %-32s | %-10s | $%-5.2f |\n", game.getName(), game.getGenre(), game.getPrice());
        }

        System.out.println("+----------------------------------+------------+-------+");
    }

    private void displayGamesByGenre() {
        scanner.nextLine();
        System.out.print(Colors.GREEN + "Enter the genre: " + Colors.RESET);
        String genre = scanner.nextLine();
        GameInitializer gameInitializer = new GameInitializer();
        gameInitializer.initializeGames();
        ArrayList<Game> gamesByGenre = gameInitializer.getGamesByGenre(genre);

        if (gamesByGenre.isEmpty()) {
            System.out.println(Colors.RED + "No games found for the specified genre." + Colors.RESET);
        } else {
            System.out.println("+----------------------------------+------------+-------+");
            System.out.println("| Title                            | Genre      | Price |");
            System.out.println("+----------------------------------+------------+-------+");

            for (Game game : gamesByGenre) {
                System.out.printf("| %-32s | %-10s | $%-5.2f |\n", game.getName(), game.getGenre(), game.getPrice());
            }

            System.out.println("+----------------------------------+------------+-------+");
        }
    }

}
