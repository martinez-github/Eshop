
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public void Register() throws IOException {
        System.out.println("ddd");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("app/ui/RegisterForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Registration window");
        stage.setScene(scene);
        stage.show();
    }
 
    public void Login() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("app/ui/LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Login window");
        stage.setScene(scene);
        stage.show();
    }

    public void OpenShop(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("app/ui/MainWindowNew.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        app.ui.MainWindowController controller = fxmlLoader.getController();
        controller.displayCategories();
        controller.displayItemsOnStartup();
        stage.setTitle("E-Shop Application");
        stage.setScene(scene);
        stage.show();
    }
}



