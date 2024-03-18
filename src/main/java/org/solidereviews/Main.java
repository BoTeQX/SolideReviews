package org.solidereviews;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.MainMenu;

public class Main {
    public static void main(String[] args) {
        String title = "MAIN MENU";
        String[] menuItems = {"Games", "Option 2", "Option 3", "Admin"};
        Menu mainMenu = new MainMenu();
        mainMenu.displayMenu(title, menuItems);
    }
}
