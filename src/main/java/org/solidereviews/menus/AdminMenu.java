package org.solidereviews.menus;

import org.solidereviews.interfaces.Menu;

public class AdminMenu implements Menu {
    @Override
    public void displayLogo() {
        System.out.println("     ▀▄   ▄▀                                  ▀▄   ▄▀     ");
        System.out.println("    ▄█▀███▀█▄    ─── Solide™ ──              ▄█▀███▀█▄    ");
        System.out.println("   █▀███████▀█            ── Reviews ────   █▀███████▀█   ");
        System.out.println("   ▀ ▀▄▄ ▄▄▀ ▀                              ▀ ▀▄▄ ▄▄▀ ▀   ");
        System.out.println();
    }

    @Override
    public void displayMenu() {
        displayLogo();
        System.out.println("placeholder for admin menu options");
    }

    @Override
    public int getUserChoice() {
        return 0;
    }

    @Override
    public void processUserChoice(int choice) {

    }

    @Override
    public void switchToMenu(Menu menu) {

    }

    @Override
    public void clearScreen() {

    }

    @Override
    public void backToPreviousMenu() {

    }

    @Override
    public void closeProgram() {

    }
}
