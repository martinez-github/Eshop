package app.ui;

import app.backend.models.Item;
import app.backend.repositories.ItemsRepository;
import app.backend.repositories.UserRep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ItemAdminViewController {
    private final ItemsRepository repository;
    private final List<Item> itemsList;
    private final UserRep repository1= new UserRep();
    @FXML
    Pane contentPane;
    @FXML
    Button DeleteButton;

    public ItemAdminViewController() throws Exception {
        repository = new ItemsRepository();
        itemsList = repository.getItems();
    }

    public void displayItem(Item item) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemPublicView.fxml"));
        Parent root = loader.load();
        ItemPublicViewController controller = loader.getController();
        controller.displayItem(item);
        contentPane.getChildren().add(root);}


    public void DeleteItem(ActionEvent actionEvent) throws IOException {
  System.out.println(actionEvent.getSource());
        for(Item i : itemsList){

        DeleteButton.getId();

    }



    }
     /*

        public List<Item> getItems() {
            List<Item> itemsList = new ArrayList<Item>();
            try {
                String sql = "select id, title, price, description, image from items";
                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    itemsList.add(new Item(id, title, description, price, image));
                }
            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            } finally {
                return itemsList;
            }*/


    public void Click(MouseEvent mouseEvent) throws IOException {

}
}


