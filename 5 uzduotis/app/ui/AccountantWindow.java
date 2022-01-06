package app.ui;

import app.backend.models.Item;
import app.backend.repositories.ItemsRepository;
import app.backend.repositories.UserRep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class AccountantWindow implements Initializable {
    private final ItemsRepository repository = new ItemsRepository();
    final public UserRep reepo= new UserRep();
    private Connection conn;
    private List<Item> itemList2;
    private static FileWriter file;
    @FXML
    private ImageView View;
    @FXML
    ChoiceBox Boxie;
    @FXML
    Pane LIST;
    final ObservableList ChoiceboxList = FXCollections.observableArrayList();

    public AccountantWindow() throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    Fill();


        Boxie.setItems(ChoiceboxList);

    }
    public void Fill(){
        try{
            ResultSet rs;
        String querry ="Select nick from user";
        PreparedStatement preparedStmt = conn.prepareStatement(querry);
        rs=preparedStmt.executeQuery();
        while(rs.next()){
            ChoiceboxList.add(rs.getString("nick"));
        }
        preparedStmt.close();

    }catch(Exception e){

        }
}

    public void loadFxml () throws IOException {

        AnchorPane newLoadedPane =  FXMLLoader.load(getClass().getResource("/app/ui/ItemHistory.fxml"));
        List<Node> parentChildren = ((AnchorPane)LIST.getParent()).getChildren();

        // replace the child that contained the old secPane
        parentChildren.set(parentChildren.indexOf(LIST), newLoadedPane);

        // store the new pane in the secPane field to allow replacing it the same way later
        LIST = newLoadedPane;
     //   LIST.getChildren().add(newLoadedPane);
    }


    public void Submit(ActionEvent actionEvent) throws IOException {
    // if(reepo.GetLoggedInUser().getClassname()=="app.backend.models.Accountant"){
            reepo.ManipulationOfUser(String.valueOf(Boxie.getSelectionModel().getSelectedItem()));
   //  }

        try {
            loadFxml();
        } catch (IOException e) {
            e.printStackTrace();
        }
        itemList2=repository.ItemsHistory();
        file = new FileWriter("JSON.json");
        try {
        GsonBuilder builder = new GsonBuilder();
         builder.setPrettyPrinting();
         Gson gson = builder.create();
            String js= gson.toJson(reepo.GetLoggedInUser().GetNickname());
        // Serializavimas
            file.write(js);
        for(Item u : itemList2){

         String jsonString = gson.toJson(u);
      //  System.out.println(jsonString); // Išvedama objekto informacija JSON formatu
            file.write(jsonString);
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonString);
    }

        }catch(IOException e){
         e.printStackTrace();
        }
    try{
        file.flush();
        file.close();

    }catch (IOException e){
        e.printStackTrace();
    }


    }

    public void Close(MouseEvent mouseEvent) {
        reepo.ManipulationOfUser("Sonny");
        final Node source = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
