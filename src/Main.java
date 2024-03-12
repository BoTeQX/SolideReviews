public class Main {
    public static void main(String[] args) {
        // Create an instance of MainMenu and call the displayMainMenu method
        MainMenu mainMenu = new MainMenu();
        mainMenu.displayMainMenu();

        // Create an instance of GameInitializer and call the initializeGames method
        GameInitializer gameInitializer = new GameInitializer();
        gameInitializer.initializeGames();
    }

}