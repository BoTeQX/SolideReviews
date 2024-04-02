package org.solidereviews;

import org.solidereviews.games.Game;
import org.solidereviews.games.GameController;
import org.solidereviews.utils.FileManager;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.MainMenu;



public class Main {
    public static void main(String[] args) {

        GameController.initiateGames();

        FileManager fileManager = new FileManager();
        new MainMenu().initiateMenu();
    }


}
