package app.backend.models;

public class Admin extends User {

    public  Admin(){
        super();
    }
    public Admin(String name, String surname, String date, String nickName, String password) throws Exception {
        super(name, surname, date, nickName, password);
    }
}
