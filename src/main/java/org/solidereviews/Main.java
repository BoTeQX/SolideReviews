package org.solidereviews;

import org.solidereviews.games.GameController;
import org.solidereviews.utils.FileManager;
import org.solidereviews.menus.MainMenu;



public class Main {
    public static void main(String[] args) {
        new FileManager(); // Initialize file manager to create save files if they don't exist to save and update data
        GameController.initiateGames(); // Initialize games to load games from file
        new MainMenu().initiateMenu(); // Start the main menu when the program starts

    }

}




