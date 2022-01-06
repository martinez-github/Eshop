package app.backend.repositories;

import app.backend.models.Category;
import app.backend.models.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemsRepository {

    private Connection conn;
    public String Choice;
private final UserRep repo = new UserRep();
    public ItemsRepository() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
final public String Change(String x){
        Choice=x;
        return Choice;
}
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
        }
    }

    public List<Category> getCategories() {
        List<Category> categoriesList = new ArrayList<Category>();
        try {
            String sql = "select id, title from categories";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                categoriesList.add(new Category(id, title));
            }
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        } finally {
            for (Category c : categoriesList) {
                c.setItems(getItems(c.getId()));
            }
            return categoriesList;
        }
    }

    private List<Item> getItems(int categoryId) {
        List<Item> itemsList = new ArrayList<Item>();
        try {
            String sql = "select id, title, price, description, image from items " +
                    "where categoryId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);

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
        }
    }
    public List<Item> getItems2() {
        List<Item> itemsList2 = new ArrayList<Item>();
        try {

            String sql = "select idwishlist, name, price, description, image from wishlist " +
                    "where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, repo.GetLoggedInUser().GetNickname());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idwishlist");
                String title = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String image = rs.getString("image");
                itemsList2.add(new Item(id, title, description, price, image));
            }
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        } finally {
            return itemsList2;
        }}
        public List<Item> ItemsHistory() {
            List<Item> itemsList3 = new ArrayList<Item>();
            try {

                Choice=repo.GetLoggedInUser().GetNickname();
                String sql = "select id, name, price, description, image from history " +
                        "where user='"+ Choice +"'";
                PreparedStatement ps = conn.prepareStatement(sql);
                //ps.setString(1, repo.GetLoggedInUser().GetNickname());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("name");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    itemsList3.add(new Item(id, title, description, price, image));
                }
            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            } finally {
                return itemsList3;
            }
    }}

