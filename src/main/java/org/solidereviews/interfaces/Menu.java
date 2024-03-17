package org.solidereviews.interfaces;

public interface Menu {
    void displayLogo();
    void displayMenu();
    int getUserChoice();
    void processUserChoice(int choice);
    void switchToMenu(Menu menu);
    void backToPreviousMenu();
    void clearScreen();
    void closeProgram();

}
