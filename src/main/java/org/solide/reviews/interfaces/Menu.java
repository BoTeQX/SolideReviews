package org.solide.reviews.interfaces;

public interface Menu {
    void displayLogo();
    void displayMenu();
    int getUserChoice();
    void processUserChoice(int choice);
    void clearScreen();
    void closeProgram();

}
