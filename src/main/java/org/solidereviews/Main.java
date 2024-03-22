package org.solidereviews;

import org.solidereviews.interfaces.Menu;
import org.solidereviews.menus.MainMenu;

public class Main {
    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.initiateMenu();
    }


}
