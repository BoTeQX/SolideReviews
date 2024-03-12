import java.util.ArrayList;
public class GameInitializer {

    ArrayList<Game> games = new ArrayList<Game>();

    public void initializeGames() {
        Game game1 = new Game("Game 1", "Action", 49.99);
        Game game2 = new Game("Game 2", "Puzzle", 59.99);
        Game game3 = new Game("Game 3", "RPG", 69.99);

        games.add(game1);
        games.add(game2);
        games.add(game3);
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<Game> getGamesByGenre(String genre) {
        ArrayList<Game> gamesByGenre = new ArrayList<Game>();
        for (Game game : games) {
            if (game.getGenre().equals(genre)) {
                gamesByGenre.add(game);
            }
        }
        return gamesByGenre;
    }
}
