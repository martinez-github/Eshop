package app.backend.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import app.backend.models.Accountant;
import app.backend.models.User;
import app.backend.models.Admin;

 public class UserRep {
     private static List<User> userList;
     private static User loggedInUser;
private Connection Conn;
     public UserRep() throws Exception{
         try {
             Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root", "wm56ljwo");
             //System.out.println("All good connected");
         } catch (SQLException e) {
             System.out.println(e.getMessage());
             System.out.println("Something went wrong.");
         }

         if (userList == null) {
             userList = new ArrayList<>();
             try {
                 Statement stmt;
                 ResultSet rs;
                 stmt = Conn.createStatement();
                 rs = stmt.executeQuery("SELECT * FROM eshop.user");
                 while (rs.next()) {

                     String name1= String.valueOf(rs.getString("name"));
                     String surename1= String.valueOf(rs.getString("surname"));
                     String birth= rs.getString("birthday");
                     String nick1= rs.getString("nick");
                     String pass1= rs.getString("password");
                     userList.add(new User(name1,surename1,birth,nick1,pass1));

                 }
             } catch (SQLException exc) {
                 exc.printStackTrace();
             }
             userList.add(new Admin("Martin", "Mar", "1998-04-12", "admin", "admin"));
             userList.add(new Accountant("Martin","Marcinkevic","1998-04-12","finansai","finansai"));
             for (User user : userList) {
                 System.out.println(user);
             }
         }
     }

     public void Register(String name, String surname, String birthdate, String nickname, String password) throws  Exception{
         userList.add(new User(name, surname, birthdate, nickname, password));
         userList.stream().forEach(System.out::println);
     }
     public void RegisterAdmin(String name, String surname, String birthdate, String nickname, String password) throws  Exception{
         userList.add(new Admin(name, surname, birthdate, nickname, password));
         userList.stream().forEach(System.out::println);

     }

     public void Login(String nickName, String password) throws Exception {
         loggedInUser = null;
         for (User u : userList){
          //  System.out.println("GET - > " + u.GetNickname());
            //System.out.println("GET - > " + u.GetPass());
            if(u.GetNickname().equals(nickName) && u.GetPass().equals(password)){
                  loggedInUser=u;
                  System.out.println(u);
                  break;
            }
         }
         if (loggedInUser == null){
             throw new Exception("wrong credentials");
         }
     }
    public void ManipulationOfUser(String x){
         loggedInUser=null;
         for(User u : userList){
             if(u.GetNickname().equals(x)){
                 loggedInUser=u;
                 System.out.println(u);
                 break;
             }
         }

    }
    public void RemoveUser(int i) throws Exception{
         if (i == -1)
             throw new Exception("nothing was selected");

        if (userList.get(i).equals(loggedInUser)){
            throw new Exception("cannot delete your self");
        }
         userList.remove(i);
    }

    public List<User> GetUsers() {
        return userList;
         }

    public String GetClassName(){
         if(loggedInUser==null){
             return "Out";
         }
         else{
             return loggedInUser.getClassname();
         }
    }
    public User GetLoggedInUser ()
     {
         return loggedInUser;
     }
     public  void LogOut()
     {
         loggedInUser = null;
     }
public String SetClassName(String s){
    if(loggedInUser==null){
        return "Out";
    }
    else{
        return loggedInUser.setClassnname(s);
    }
}

}
