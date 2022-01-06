package app.backend.models;

public class Accountant extends User{
    public Accountant(){
        super();
    }
    public Accountant(String name, String surname, String date, String nickName, String password) throws Exception {
        super(name, surname, date, nickName, password);
    }

}
