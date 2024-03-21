package org.solidereviews;

import org.solidereviews.games.Game;
import org.solidereviews.games.GameController;
import org.solidereviews.utils.FileManager;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.MainMenu;



public class Main {
    public static void main(String[] args) {
        Game game1 = new Game("Game 1", "Shooter", 19.99);
        Game game2 = new Game("Game 2", "RPG", 14.99);
        Game game3 = new Game("Game 3", "Survival", 29.99);
        GameController gameController = new GameController();
        gameController.addToGameList(game1);
        gameController.addToGameList(game2);
        gameController.addToGameList(game3);

        FileManager fileManager = new FileManager();
        Menu mainMenu = new MainMenu();
        String title = "MAIN MENU";
        String[] menuItems = {"Games", "Reviews", "Admin"};
        mainMenu.displayMenu(title, menuItems);


    }


}
