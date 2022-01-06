package app.ui;

import app.backend.models.Category;
import app.backend.models.Item;
import app.backend.models.User;
import app.backend.repositories.ItemsRepository;
import app.backend.repositories.UserRep;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    private Connection conn;
    private final UserRep repository1= new UserRep();
    @FXML
  Button ADMIN;
    @FXML
    Button Accountant;
    @FXML
    Button ItemHistory;
    @FXML
    VBox sideMenu;
    @FXML
    FlowPane content;
    @FXML
    Button wishlist;
    @FXML
    ListView<Item> LISTV = new ListView<>();
   // private UserRep repository= new UserRep();
    private final ItemsRepository repository;
    private final List<Item> itemsList;
    private final List<Category> categoriesList;
    private final List<Item> itemsList2;

    private static boolean isAdmin = false;

    public MainWindowController() throws Exception {
        repository = new ItemsRepository();
        itemsList = repository.getItems();
        categoriesList = repository.getCategories();
        itemsList2=repository.getItems2();
    }

    public void displayCategories() {
        for (Category c : categoriesList) {
            Button categoryButton = new Button(c.getTitle());
            categoryButton.setPrefWidth(Double.MAX_VALUE);
            CategoryButtonEventHandler handler = new CategoryButtonEventHandler(c, this);
            categoryButton.setOnAction(handler);
            sideMenu.getChildren().add(categoryButton);
        }
        for(Item i : itemsList2){
            System.out.println(i.getTitle());
        }
    }

    public void displayItemsOnStartup() throws IOException {
        content.getChildren().clear();
        for (Item item : itemsList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemPublicView.fxml"));
            Parent root = loader.load();
            ItemPublicViewController controller = loader.getController();
            controller.displayItem(item);

            content.getChildren().add(root);

        }
    }

    public void displayItems(List<Item> items) throws IOException {
        content.getChildren().clear();
            for (Item item : items) {
                System.out.println(item.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemPublicView.fxml"));
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ItemPublicViewWishList.fxml"));
                Parent root = loader.load();
                Parent root1 = loader1.load();
                ItemPublicViewController controller = loader.getController();
                ItemPublicViewWishList controller1= loader1.getController();
                controller1.displayItem(item);
                controller.displayItem(item);
                content.getChildren().add(root);
            }
        }



    public void onAdminClicked(ActionEvent actionEvent) {
        if (repository1.GetClassName().equals("app.backend.models.Admin")) {
          System.out.println("Admin");
        }
        isAdmin = !isAdmin;
    }

    public void AdminThings(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/ui/AdminWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Logged window");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       if (repository1.GetClassName().equals("app.backend.models.Admin")) {
            ADMIN.setVisible(true);
           wishlist.setVisible(false);
           ItemHistory.setVisible(false);
        }
        if (repository1.GetClassName().equals("app.backend.models.User")) {
            ADMIN.setVisible(false);
            wishlist.setVisible(true);
            ItemHistory.setVisible(true);
        }
        if (repository1.GetClassName().equals("app.backend.models.Accountant")) {
           Accountant.setVisible(true);
        }
        for(Item i : itemsList2){
            System.out.println(i.getTitle());
        }

    }

    public void WishlistOpen(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/ui/WishList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("E-Shop Application");
        stage.setScene(scene);
        stage.show();}

    public void ItemHis(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/ui/ItemHistory.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("E-Shop Application");
        stage.setScene(scene);
        stage.show();}

    public void Accountantpart(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/ui/AccountantWindoW.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("E-Shop Application");
        stage.setScene(scene);
        stage.show();}

    }



