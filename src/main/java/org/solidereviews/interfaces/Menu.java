package org.solidereviews.interfaces;

public interface Menu {
    void displayLogo();
    void displayMenu();
    int getUserChoice();
    void processUserChoice(int choice);
    void openNewMenu(Menu menu);
    void clearScreen();
    void closeProgram();

}
