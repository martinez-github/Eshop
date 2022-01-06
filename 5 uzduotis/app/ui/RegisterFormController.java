package app.ui;

import app.backend.models.Admin;
import app.backend.repositories.UserRep;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegisterFormController {
     private final UserRep repository= new UserRep();
     private Boolean admin;

     @FXML
     TextField NameInput;
     @FXML
     TextField PassInput;
     @FXML
     TextField SurnameInput;
     @FXML
     TextField DateInput;
     @FXML
     TextField NickInput;
     @FXML
     Label LabelPopUp;
     @FXML
     Label LabelPopUp1;
     @FXML
     Label LabelPopUp2;
     @FXML
     Label NickLabel;
     @FXML
     Label PassLabel;
     @FXML
     Button buttonA;
     @FXML
     Button buttonU;


     public RegisterFormController() throws Exception {
     }

     public void User() throws Exception {
         admin = false;
         Submitted();
     }
     public void Admin() throws Exception {
         admin = true;
         Submitted();
     }
     public void Submitted() throws Exception {
         Admin temp = new Admin();
         try {
             temp.setName(NameInput.getText());
         }catch (Exception exc){
             LabelPopUp.setText(exc.getMessage());
         }

         try {
             temp.setSurname(SurnameInput.getText());
         }catch (Exception exc){
             LabelPopUp2.setText(exc.getMessage());
         }

         try {
             temp.setBirthDate(DateInput.getText());
         }catch (Exception exc){
             LabelPopUp1.setText(exc.getMessage());
         }

         try {
             temp.setNickName(NickInput.getText());
         }catch (Exception exc){
             NickLabel.setText(exc.getMessage());
         }

         try {
             temp.setPassword(PassInput.getText());
         }catch (Exception exc){
             PassLabel.setText(exc.getMessage());
         }

         Stage stage;
         if (admin) {
             repository.RegisterAdmin(NameInput.getText(), SurnameInput.getText(), DateInput.getText(), NickInput.getText(), PassInput.getText());
             stage = (Stage) buttonA.getScene().getWindow();
             stage.close();
         }

         if (!admin) {
             try{
                 Connection conn;
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop","root","wm56ljwo");
                 Statement statement=conn.createStatement();
                 String query = "INSERT INTO eshop.user (name, surname, birthday, nick, password)"+ " values (?, ?, ?, ?,  ?)";
                 PreparedStatement preparedStmt = conn.prepareStatement(query);
                 preparedStmt.setString(1, NameInput.getText());
                 preparedStmt.setString(2, SurnameInput.getText());
                 preparedStmt.setString(3, DateInput.getText());
                 preparedStmt.setString(4, NickInput.getText());
                 preparedStmt.setString(5,PassInput.getText());
                 preparedStmt.execute();
                 int rowsaffected= statement.executeUpdate(query);
                 System.out.println("Rows effected "+ rowsaffected);
             }
             catch(Exception exc) {
                 System.out.println(exc.getMessage());}
             repository.Register(NameInput.getText(), SurnameInput.getText(), DateInput.getText(), NickInput.getText(), PassInput.getText());
             stage = (Stage) buttonU.getScene().getWindow();
             stage.close();
         }

     }
 }
