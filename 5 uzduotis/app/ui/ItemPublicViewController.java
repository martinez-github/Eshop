package app.ui;

import app.backend.models.Item;
import app.backend.repositories.ItemsRepository;
import app.backend.repositories.UserRep;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class ItemPublicViewController implements Initializable {
    private final UserRep repository1 = new UserRep();
    private Connection Conn;
    @FXML
    private final ObservableList<String> CommentsPoP = FXCollections.observableArrayList();
    @FXML
    private final ObservableList<String> CommentsPoPAdmin = FXCollections.observableArrayList();
    @FXML
    ImageView image;
    @FXML
    Label titleLabel;
    @FXML
    Label descriptionLabel;
    @FXML
    Label priceLabel;
    @FXML
    private Button Add;
    @FXML
    Button Remove;
    @FXML
    AnchorPane Anchor;
    @FXML
    ChoiceBox CommentsBox;
    @FXML
    TextField CommentField;
    @FXML
    Button CommentButton;
    @FXML
    Button CommentDeleteButton;

    private final List<Item> itemsList;
    private final ItemsRepository repository;

    public ItemPublicViewController() throws Exception {
        repository = new ItemsRepository();
        itemsList = repository.getItems();
    }

    public void displayItem(Item item) {
        titleLabel.setText(item.getTitle());
        descriptionLabel.setText(item.getDescription());
        priceLabel.setText(String.valueOf(item.getPrice()));
        File file = new File("pics/" + item.getImage());
        image.setImage(new Image(file.toURI().toString()));

        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        if(repository1.GetClassName().equals("app.backend.models.User")){
        try {
            System.out.println(descriptionLabel.getText());
            stmt = Conn.createStatement();
            rs = stmt.executeQuery("SELECT content,user FROM eshop.comments where identificator='"+ descriptionLabel.getText() +"'");
         //   rs2 = stmt.executeQuery("SELECT id,content,user FROM eshop.comments where identificator='"+ descriptionLabel.getText() +"'");

            while (rs.next()) {
                CommentsPoP.add(( rs.getString("content")) + "  " + "User - " + "  "+rs.getString("user"));
            }

            CommentsBox.setItems(CommentsPoP);
        } catch (SQLException e) {
        }}

        try {
            System.out.println(descriptionLabel.getText());
            stmt = Conn.createStatement();

               rs2 = stmt.executeQuery("SELECT id,content,user FROM eshop.comments where identificator='"+ descriptionLabel.getText() +"'");

            while (rs2.next()) {
                CommentsPoPAdmin.add((rs2.getInt(("id"))+" "+ rs2.getString("content")) + "  " + "User - " + "  "+rs2.getString("user"));
            }
            if(repository1.GetClassName().equals("app.backend.models.Admin")){
            CommentsBox.setItems(CommentsPoPAdmin);}
        } catch (SQLException e) {
        }
    }

    public void Remove(ActionEvent actionEvent) {

        System.out.println(titleLabel.getText());
        {
            try {
                String query1 = "DELETE FROM eshop.items WHERE title = '" + titleLabel.getText() + "'";
                Statement stmt = Conn.createStatement();
                int rowsaffected = stmt.executeUpdate(query1);
                System.out.println("Rows effected " + rowsaffected);
                if(rowsaffected>0){
                    Label Text = new Label();
                    Pane pane= new Pane();
                    Text.setText("Deleted");
                    Stage stage = new Stage();
                    pane.getChildren().add(Text);
                    Scene scene =  new Scene(pane, 200 ,200);
                    stage.setScene(scene);
                    stage.show();

                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }

        }
    }

    public void Add(ActionEvent actionEvent) {

        try{
            ResultSet rs;
            Statement stmt=Conn.createStatement();
            rs = stmt.executeQuery("select image from items where title='"+ titleLabel.getText() +"'");
            rs.next();
           String imageY= rs.getString("image");
           System.out.println(imageY);
            String query = "INSERT INTO eshop.wishlist (name, price,description,image,username)"+ " values (?, ?,?,?,?)";
            PreparedStatement preparedStmt = Conn.prepareStatement(query);
            preparedStmt.setString(1, titleLabel.getText());
            preparedStmt.setString(2,priceLabel.getText());
            preparedStmt.setString(3,descriptionLabel.getText());
            preparedStmt.setString(4, imageY);
            preparedStmt.setString(5,repository1.GetLoggedInUser().GetNickname());
            preparedStmt.execute();
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
            System.out.println("All good connected_1");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong.");
        }
        if (repository1.GetClassName().equals("app.backend.models.Admin")) {
            CommentsBox.setVisible(true);
            CommentDeleteButton.setVisible(true);
            Remove.setVisible(true);
            CommentField.setVisible(true);
        }
        if (repository1.GetClassName().equals("app.backend.models.User")) {

            Add.setVisible(true);
            CommentsBox.setVisible(false);
            CommentField.setVisible(false);
            CommentButton.setVisible(false);
          //  setInvis();
        }
        if(repository1.GetClassName().equals("app.backend.models.Accountant")){
            Add.setVisible(false);
            CommentsBox.setVisible(false);
            CommentField.setVisible(false);
            CommentButton.setVisible(false);
        }

    }

    public void AddComent(ActionEvent actionEvent) {
        try{
            ResultSet rs;
            Statement stmt=Conn.createStatement();
            rs = stmt.executeQuery("select image from items where title='"+ titleLabel.getText() +"'");
            rs.next();
            String imageY= rs.getString("image");
            System.out.println(imageY);
            String query = "INSERT INTO eshop.comments (content, user,identificator)"+ " values (?, ?,?)";
            PreparedStatement preparedStmt = Conn.prepareStatement(query);
            preparedStmt.setString(1, CommentField.getText());
            preparedStmt.setString(2,repository1.GetLoggedInUser().GetNickname());
            preparedStmt.setString(3,descriptionLabel.getText());
            preparedStmt.execute();
            CommentField.clear();
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());}
    }


    public void DeleteComment(ActionEvent actionEvent) {
        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
            String query1 = "DELETE FROM eshop.comments WHERE id = '" + CommentField.getText() + "'";
            Statement stmt = Conn.createStatement();
            int rowsaffected = stmt.executeUpdate(query1);
            System.out.println("Rows effected " + rowsaffected);
    }catch(SQLException exe){
            System.out.println(exe);
        }
}}