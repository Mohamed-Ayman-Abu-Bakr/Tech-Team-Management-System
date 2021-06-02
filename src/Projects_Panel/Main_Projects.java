package Projects_Panel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_Projects extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Projects.fxml"));
        primaryStage.setTitle("Projects Panel");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
        System.out.println("start initialized");
    }


    public static void main(String[] args) {
        launch(args);
        System.out.println("main initialized");
    }
}
