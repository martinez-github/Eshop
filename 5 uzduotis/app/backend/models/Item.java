package app.backend.models;

import javafx.scene.Node;

public class Item  {
    public final int id;
    public final String title;
    public final String description;
    public final double price;
    public final String image;

    public Item(int id, String title, String description, double price, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }


    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
