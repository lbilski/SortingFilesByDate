package pl.lukaszbilski.SortingFilesByDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.lukaszbilski.SortingFilesByDate.models.Utils;

public class Main extends Application {

    private Utils utils = new Utils();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
        primaryStage.setTitle("Sortowanie");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setResizable(false);
        primaryStage.show();

        utils.creatingFolders();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
