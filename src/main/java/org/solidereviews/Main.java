package org.solidereviews;

import org.solidereviews.games.GameController;
import org.solidereviews.utils.FileManager;
import org.solidereviews.secret.GlobalKeyListener;
import org.solidereviews.menus.MainMenu;

public class Main {
    public static void main(String[] args) {
        new FileManager();
        GameController.initiateGames();
        new GlobalKeyListener();
        new MainMenu().initiateMenu();

    }

}
