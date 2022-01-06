package app.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final int id;
    private final String title;
    private List<Item> itemsList;
    private final List<Item> itemsList2;
    public Category(int id, String title) {
        this.id = id;
        this.title = title;
        itemsList = new ArrayList<Item>();
        itemsList2 = new ArrayList<Item>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Item> getItems() {
        return itemsList;
    }


    public List<Item> getItems2() {
        return itemsList2;
    }
    public void setItems(List<Item> items) {
        itemsList = items;
    }
}
