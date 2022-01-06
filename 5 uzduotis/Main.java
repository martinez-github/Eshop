
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //UserRep repository= new UserRep();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Control Window");
        stage.setScene(scene);
        stage.show();
        Parent MainW= FXMLLoader.load(getClass().getResource("app/ui/RegisterForm.fxml"));
        Parent LoginW= FXMLLoader.load(getClass().getResource("app/ui/LogIn.fxml"));
    }

    public static void main(String[] args) {
        launch(args);
    }{
    }
}
