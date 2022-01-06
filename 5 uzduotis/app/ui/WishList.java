package app.ui;

import app.backend.models.Item;
import app.backend.models.User;
import app.backend.repositories.ItemsRepository;
import app.backend.repositories.UserRep;
import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WishList implements Initializable {
    private Connection Conn;
private final UserRep repo= new UserRep();
private final ItemsRepository repository = new ItemsRepository();
private List<Item> itemList2;
@FXML
FlowPane Content;
@FXML
Label price;
@FXML
Label Sales;

    public WishList() throws Exception {

      /*  try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root", "root");
            Statement statement = Conn.createStatement();
            String query = "INSERT INTO test_db.teacher (nick, password)" + " values (?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, NickInput2.getText());
            preparedStmt.setString(2, PassInput2.getText());
            preparedStmt.execute();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }*/

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(repo.GetLoggedInUser().GetBirthdays());
        ArrayList myList = new ArrayList<Double>();
         String[] priceArray = new String[4];
         double sum=0;
        int counterr=0;
        itemList2=repository.getItems2();
        for (Item item : itemList2) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/ItemPublicViewWishList.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ItemPublicViewWishList controller = loader.getController();
            controller.displayItem(item);
            Content.getChildren().add(root);

        }
        for(Item item : itemList2){

            myList.add(item.getPrice());
            counterr++;
        }

        for(int i=0;i<counterr;i++){
            sum= (double) myList.get(i) + sum;

        }
        LocalDate to = LocalDate.now();
            //    LocalDate.parse("2001-09-19");
       LocalDate from= LocalDate.parse(repo.GetLoggedInUser().GetBirthdays());
       LocalDate now= LocalDate.now();
        long days = Period.between(from, to).getDays();
        long months = Period.between(from, to).getMonths();
        boolean  gim =true;
        System.out.println(days);
        System.out.println(months);
        String saled ="2001-01-22";
    try {
        Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
        Statement stmt=Conn.createStatement();
        String name = null;

        ResultSet rs;
        String query ="Select username from eshop.sale where username='"+ repo.GetLoggedInUser().GetNickname() +"' ";
       rs = stmt.executeQuery(query);
        while (rs.next()) {
             name = rs.getString("username");

        }
        System.out.println(name);
        if (days > -8 && days < 8 && months == 0) {
           System.out.println("LUCKY");


            if(String.valueOf(name).equals(repo.GetLoggedInUser().GetNickname())) {
            System.out.println("TOKS YRA ");
            }
            else{
            String query2 = "INSERT INTO eshop.sale (username, userbirthday,saledate)"+ " values (?,?,?)";
            PreparedStatement preparedStmt = Conn.prepareStatement(query2);

                preparedStmt.setString(1,repo.GetLoggedInUser().GetNickname());
                preparedStmt.setString(2,repo.GetLoggedInUser().GetBirthdays());
                preparedStmt.setString(3, saled);

                preparedStmt.execute();}
        }

        String query3 ="Select saledate from eshop.sale where username='"+ repo.GetLoggedInUser().GetNickname() +"' ";
        rs = stmt.executeQuery(query3);
        while (rs.next()) {
            saled=rs.getString("saledate");
        }
        System.out.println("dar"+saled);
    }catch(SQLException exc){
            exc.printStackTrace();
        }

    long years =Period.between(LocalDate.parse(saled),now).getYears();
    System.out.println(years);
    if(days > -8 && days < 8 && months == 0 && years!=0 ){
        double sum2=0;
        sum2= sum /10;
        sum=sum-sum2;
        System.out.println(sum2);
        System.out.println(sum);
        Sales.setText("Price with 10% off");
        price.setText(String.valueOf(sum));
    }
    else{
        System.out.println("GAVO JAUUUU");
        price.setText(String.valueOf(sum));
        Sales.setText("Current price of items");
    }
    }

    public void WishListBuy(ActionEvent actionEvent) {
        LocalDate now= LocalDate.now();
        System.out.println(now);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
             String sql = "UPDATE sale SET saledate=? where username=?";
             PreparedStatement ps = Conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(now));
            ps.setString(2, repo.GetLoggedInUser().GetNickname());
            ps.executeUpdate();
        }catch(Exception exc){
            System.out.println(exc);
        }

        try{
            itemList2=repository.getItems2();
            ResultSet rs;
            Statement stmt=Conn.createStatement();
            rs = stmt.executeQuery("select image from wishlist");
            rs.next();
            String imageY= rs.getString("image");
            System.out.println(imageY);
            String query = "INSERT INTO eshop.history (name, price,image,description,user)"+ " values (?, ?,?,?,?)";
            PreparedStatement preparedStmt = Conn.prepareStatement(query);
            for(Item item : itemList2){
            preparedStmt.setString(1, item.getTitle());
            preparedStmt.setDouble(2,item.getPrice());
            preparedStmt.setString(3,item.getImage());
            preparedStmt.setString(4,item.getDescription());
            preparedStmt.setString(5,repo.GetLoggedInUser().GetNickname());
            preparedStmt.execute();
        }}
        catch(SQLException exc) {
            System.out.println(exc.getMessage());}

 try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
        String sql1 = "DELETE from eshop.wishlist where username=?";
        PreparedStatement ps = Conn.prepareStatement(sql1);
        ps.setString(1, repo.GetLoggedInUser().GetNickname());
        ps.executeUpdate();
    }catch(Exception exc){
        System.out.println(exc);
    }}
    }

    /*public void displayItems(List<Item> items) throws IOException {
        Content.getChildren().clear();
        for (Item item : items) {
            System.out.println(item.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemPublicView.fxml"));
            Parent root = loader.load();
            ItemPublicViewController controller = loader.getController();
            controller.displayItem(item);
            Content.getChildren().add(root);
        }
    }


}*/
