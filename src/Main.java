import games.GameController;
import utils.FileManager;
import secret.GlobalKeyListener;
import menus.MainMenu;

public class Main {
    public static void main(String[] args) {

        new FileManager();
        new GlobalKeyListener();

        GameController.initiateGames();
        GameController.initiateReviews();

        new MainMenu().initiateMenu();
    }
}
