package app.ui;

import app.backend.models.Item;
import app.backend.repositories.UserRep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ItemPublicViewWishList {
    private final UserRep repository1 = new UserRep();
    private Connection Conn;
    @FXML
    ImageView image;
    @FXML
    Label titleLabel;
    @FXML
    Label descriptionLabel;
    @FXML
    Label priceLabel;
    @FXML
    Button Add;
    @FXML
    Button Remove;
    @FXML
    AnchorPane Anchor;

    public ItemPublicViewWishList() throws Exception {
    }

    public void displayItem(Item item) {
        titleLabel.setText(item.getTitle());
        descriptionLabel.setText(item.getDescription());
        priceLabel.setText(String.valueOf(item.getPrice()));
        File file = new File("pics/" + item.getImage());
        image.setImage(new Image(file.toURI().toString()));
    }

    public void DeleteItem(ActionEvent actionEvent) {

        try {
            Conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
            Statement stmt= Conn.createStatement();
            String query1 = "DELETE FROM eshop.wishlist WHERE name= '"+ titleLabel.getText() +"'";
// get a connection and then in your try catch for executing your delete...
            int rowsaffected= stmt.executeUpdate(query1);
            System.out.println("Rows effected "+ rowsaffected);


        }catch (Exception exc){
            exc.printStackTrace();

        }

    }
}
