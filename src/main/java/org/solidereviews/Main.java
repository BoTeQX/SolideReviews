package org.solidereviews;

import org.solidereviews.filemanager.FileManager;
import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.MainMenu;



public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        Menu mainMenu = new MainMenu();
        String title = "MAIN MENU";
        String[] menuItems = {"Games", "Reviews", "Admin"};
        mainMenu.displayMenu(title, menuItems);
    }


}
