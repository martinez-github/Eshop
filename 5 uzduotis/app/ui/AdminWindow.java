package app.ui;

import app.backend.models.Category;
import app.backend.models.Item;
import app.backend.repositories.ItemsRepository;
import com.sun.webkit.dom.TextImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class AdminWindow implements Initializable {
    private ItemsRepository repository;
    private List<Category> categoryList;
    private Connection Conn;
    @FXML
    private final ObservableList<String> categories = FXCollections.observableArrayList();
    @FXML
    private final ObservableList<String> ForPoP = FXCollections.observableArrayList();
    @FXML
    ListView ListBox1;
    @FXML
    TextField TitleGet;
    @FXML
    TextField PriceGet;
    @FXML
    TextField DescriptionGet;
    @FXML
    TextField ImageGet;
    @FXML
    TextField CategoryId;
    @FXML
    TextField CategoyInput;
    @FXML
    ChoiceBox Boxie;

    public void AddCategory(ActionEvent actionEvent) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
            Statement statement = Conn.createStatement();
            String query = "INSERT INTO eshop.categories (title)" + " values ( ?)";
            PreparedStatement preparedStmt = Conn.prepareStatement(query);
            preparedStmt.setString(1, CategoyInput.getText());
            if(!CategoyInput.getText().equals(null)){
                Label Text = new Label();
                Pane pane= new Pane();
                Text.setText("Added");
                Stage stage = new Stage();
                pane.getChildren().add(Text);
                Scene scene =  new Scene(pane, 200 ,200);
                stage.setScene(scene);
                stage.show();
                preparedStmt.execute();}

        }
        catch(Exception exc) {
            System.out.println(exc.getMessage());}

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            repository = new ItemsRepository();
        } catch (Exception e) {
            e.printStackTrace();
        }
        categoryList = repository.getCategories();
        for(Category c: categoryList){
                System.out.println(c.getId());
            ForPoP.add(String.valueOf((c.getId())));
        }

        Boxie.setItems(ForPoP);

        ListBox1.setItems(categories);


        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
            System.out.println("All good connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong.");
        }

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = Conn.createStatement();
            rs = stmt.executeQuery("SELECT id,title FROM eshop.categories");

            while (rs.next()) {
                categories.add("Category id." + ( rs.getInt("id")) + "  " + "Category title - " + "  "+rs.getString("title"));
            }
        } catch (SQLException e) {
        }}

    public void AddItem(ActionEvent actionEvent) {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
            Statement statement = Conn.createStatement();
            String query = "INSERT INTO eshop.items (title,price,description,image,categoryId)" + " values ( ?,?,?,?,?)";
            PreparedStatement preparedStmt = Conn.prepareStatement(query);
            preparedStmt.setString(1, TitleGet.getText());
            preparedStmt.setString(2, PriceGet.getText());
            preparedStmt.setString(3, DescriptionGet.getText());
            preparedStmt.setString(4, ImageGet.getText());
            preparedStmt.setString(5, ((String) Boxie.getSelectionModel().getSelectedItem()));

            if(!TitleGet.getText().equals(null)){
                Label Text = new Label();
                Pane pane= new Pane();
                Text.setText("Added");
                Stage stage = new Stage();
                pane.getChildren().add(Text);
                Scene scene =  new Scene(pane, 200 ,200);
                stage.setScene(scene);
                stage.show();
                preparedStmt.execute();}

        }
        catch(Exception exc) {
            System.out.println(exc.getMessage());}
    }
}

