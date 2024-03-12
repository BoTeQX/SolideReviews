import java.util.ArrayList;

public class Game {
    String name;
    String genre;
    double price;


    // Constructor
    public Game(String name, String genre, double price) {
        this.name = name;
        this.genre = genre;
        this.price = price;

    }

    // Getters
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString
    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    }
}
