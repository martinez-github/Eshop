package app.ui;

import app.backend.repositories.UserRep;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LogInController {
    private final UserRep repository= new UserRep();
    @FXML
    TextField NickInputCheck;
    @FXML
    TextField PassInputCheck;

    public LogInController() throws Exception {
    }

    public void Logged(){
        String Nick = NickInputCheck.getText();
        String Pass = PassInputCheck.getText();
        System.out.println("LOGIN - > " + Nick);
        System.out.println("LOGIN - > " +Pass);

        try {
            repository.Login(Nick, Pass);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/app/ui/MainWindowNew.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            app.ui.MainWindowController controller = fxmlLoader.getController();
            controller.displayCategories();
            controller.displayItemsOnStartup();
            stage.setTitle("E-Shop Application");
            stage.setScene(scene);
            stage.show();
        }catch (Exception exc){
            //exc.getMessage()
        }
    }
}


