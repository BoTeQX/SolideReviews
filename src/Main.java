import games.GameController;
import games.Game;
import utils.FileManager;
import menus.MainMenu;
import secret.GlobalKeyListener;

public class Main {
    public static void main(String[] args) {
        new FileManager();
        GameController.initiateGames();
        new GlobalKeyListener();
        Game.readSurveysFromFile(GameController.getGames());


        new MainMenu().initiateMenu();

    }

}
