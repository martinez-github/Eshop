package app.ui;

import app.backend.models.Item;
import app.backend.repositories.ItemsRepository;
import app.backend.repositories.UserRep;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class ItemHistory extends ItemPublicViewController implements Initializable {
    private final UserRep repo= new UserRep();
    private final ItemsRepository repository = new ItemsRepository();
    private List<Item> itemList3;
    @FXML
    FlowPane ConntentPane;

    public ItemHistory() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemList3 = repository.ItemsHistory();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/app/ui/ItemPublicView.fxml"));
        FXMLLoader loader = null;
        try {
            AnchorPane pane = loader2.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ItemPublicViewController exampleController = loader2.getController();
        exampleController.titleLabel.setText("sssssssssssssss");
repository.Change("Qwerty");

        for (Item item : itemList3) {

            loader = new FXMLLoader(getClass().getResource("/app/ui/ItemPublicView.fxml"));

            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ItemPublicViewController controller2 = loader.getController();
            controller2.displayItem(item);
            ConntentPane.getChildren().add(root);

        }

    }
}
