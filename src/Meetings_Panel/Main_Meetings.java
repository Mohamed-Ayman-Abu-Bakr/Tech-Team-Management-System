package Meetings_Panel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_Meetings extends Application {

    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Meetings");
        Parent root = FXMLLoader.load(getClass().getResource("Meetings.fxml"));
        primaryStage.setScene( new Scene(root));
        primaryStage.show();
    }


}
