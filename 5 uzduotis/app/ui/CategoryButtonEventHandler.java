package app.ui;

import app.backend.models.Category;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.IOException;

public class CategoryButtonEventHandler<ActionEvent extends Event> implements EventHandler<ActionEvent> {

    private final Category category;
    private final MainWindowController mainWindow;

    public CategoryButtonEventHandler(Category category, MainWindowController mainWindow) {
        this.category = category;
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            mainWindow.displayItems(category.getItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
