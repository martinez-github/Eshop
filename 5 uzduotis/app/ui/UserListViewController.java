package app.ui;

import app.backend.models.User;
import app.backend.repositories.UserRep;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class UserListViewController {
    private final UserRep repository = new UserRep();

    @FXML
    ListView<User> Listbox1 = new ListView<>();
    @FXML
    Label TTT;
    @FXML
    Button button;
    @FXML
    protected void initialize(){
        List<User> userList= repository.GetUsers();
        Listbox1.getItems().addAll(userList);
    }
    public UserListViewController() throws Exception {
    }

    public void DeleteUser() {
        int i = Listbox1.getSelectionModel().getSelectedIndex();
        // get a handle to the stage
        try {
            repository.RemoveUser(i);
            Listbox1.getItems().remove(i);
        }catch (Exception exc){
            TTT.setText(exc.getMessage());
        }
    }
}

