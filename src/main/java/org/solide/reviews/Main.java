package org.solide.reviews;

import org.solide.reviews.interfaces.Menu;
import org.solide.reviews.menus.MainMenu;

public class Main {
    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.displayMenu();
    }
}
