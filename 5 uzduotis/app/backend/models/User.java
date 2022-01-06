package app.backend.models;

import java.time.LocalDate;
import java.time.Period;

public class User {
    protected String name;
    protected String surname;
    protected LocalDate birthDate;
    protected String nickName;
    protected String password;

    public User(){
    }
    public User(String name, String surname, String date, String nickName, String password) throws Exception
    {
        this.name = CheckField(name, "name");
        this.surname = CheckField(surname, "surname");
        this.birthDate = CheckDate(CheckField(date, "date"));
        this.nickName = CheckField(nickName, "nick name");
        this.password = CheckField(password, "password");
    }

    private String CheckField (String field, String message) throws Exception
    {
        if ("".equals(field.trim()))
            throw new Exception ("Empty " + message + " field");

        return field;
    }

    private LocalDate CheckDate (String sDate) throws Exception
    {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.parse(sDate);

        if(date.compareTo(now) > 0){
            throw new Exception("You arent even born yet!");
        }

        if(Period.between(date, now).getYears()<14){
            throw new Exception("You are under 14");
        }

        return date;
    }
    public void setName(String name) throws Exception{
        this.name = CheckField(name, "name");
    }
    public void setSurname(String surname) throws Exception{
        this.surname = CheckField(surname, "surname");
    }
    public void setBirthDate(String birthDate) throws Exception{
        String temp = CheckField(birthDate, "birhdate");
        this.birthDate = CheckDate(temp);
    }
    public void setNickName(String name) throws Exception{
        this.nickName = CheckField(name, "nickname");
    }
    public void setPassword(String name) throws Exception{
        this.password = CheckField(name, "password");
    }
    public int GetAge()
    {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    public String GetNickname(){
    return nickName;
    }
    public String GetPass(){
        return password;
    }
    public String GetBirthdays(){
        return String.valueOf(birthDate);
    }
    public void ChangePass(String oldpass,String newpass) throws Exception{
        /*
        if(!oldpass.equals(password)){
            System.out.println("OLD -> " + oldpass);
            System.out.println("NEW - > " + password);
            throw new Exception("Password doesnt match the old one");
        }
        else {
             password = newpass;
             System.out.println("changed");
        }*/

        if (!oldpass.equals(password))
        {
            throw new Exception("Password doesnt match the old one");
        }
        this.password = CheckField(newpass, "password");
    }
    @Override
    public String toString() {
        return getClassname() + "{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    String s = "Admin";
    public String getClassname() {
        return this.getClass().getName();
    }
    public String setClassnname(String s){return this.setClassnname(s);}
    
}


