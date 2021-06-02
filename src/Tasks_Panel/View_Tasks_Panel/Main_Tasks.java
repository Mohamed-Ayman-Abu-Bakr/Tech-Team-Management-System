
package Tasks_Panel.View_Tasks_Panel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main_Tasks extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Group gr = new Group();
        ToggleGroup tg = new ToggleGroup();
        
        HBox h = new HBox();
        RadioButton rb1 = new RadioButton("Done");
        RadioButton rb2 = new RadioButton("Not Done");
        
        tg.getToggles().addAll(rb1,rb2);
        h.getChildren().addAll(rb1,rb2);
        Parent root = FXMLLoader.load(getClass().getResource("Tasks.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.show();
        
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
