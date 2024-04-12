package games;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void getName() {
        Game game = new Game("name", "genre", 10.0);
        assertEquals("name", game.getName());
    }

    @Test
    public void setName() {
        Game game = new Game("name", "genre", 10.0);
        game.setName("newName");
        assertEquals("newName", game.getName());
    }

    @Test
    public void getGenre() {
        Game game = new Game("name", "genre", 10.0);
        assertEquals("genre", game.getGenre());
    }

    @Test
    public void setGenre() {
        Game game = new Game("name", "genre", 10.0);
        game.setGenre("newGenre");
        assertEquals("newGenre", game.getGenre());
    }

    @Test
    public void getPrice() {
        Game game = new Game("name", "genre", 10.0);
        assertEquals(10.0, game.getPrice(), 0.0);
    }

    @Test
    public void setPrice() {
        Game game = new Game("name", "genre", 10.0);
        game.setPrice(20.0);
        assertEquals(20.0, game.getPrice(), 0.0);
    }

    @Test
    public void getSale() {
        Game game = new Game("name", "genre", 10.0, 5);
        assertEquals(5, game.getSale());
    }

    @Test
    public void setSale() {
        Game game = new Game("name", "genre", 10.0, 5);
        game.setSale(10);
        assertEquals(10, game.getSale());
    }
}