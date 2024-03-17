package org.solide.reviews;

public interface Menu {
    void displayLogo();
    void displayMenu();
    int getUserChoice();
    void processUserChoice(int choice);
    void clearScreen();


}
