import app.ui.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Startup extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("app/ui/MainWindow.fxml"));
        Parent root = loader.load();
        MainWindowController controller = loader.getController();
        controller.displayCategories();
        controller.displayItemsOnStartup();
        primaryStage.setTitle("E-Shop Application");
        primaryStage.setScene(new Scene(root, 900, 575));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
