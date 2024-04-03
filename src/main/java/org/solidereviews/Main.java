package org.solidereviews;

import org.solidereviews.games.Game;
import org.solidereviews.games.GameController;
import org.solidereviews.utils.FileManager;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.MainMenu;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        List<String> data = List.of("Data 1", "Data 2", "Data 3");
        fileManager.writeDataFile(data);
        List<String> games = List.of("Game 1", "Game 2", "Game 3");
        fileManager.writeGamesFile(games);

        GameController.initiateGames();
        new MainMenu().initiateMenu();

    }

}




