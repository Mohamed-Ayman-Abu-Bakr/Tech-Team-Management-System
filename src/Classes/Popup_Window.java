package Classes;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Popup_Window {
    public static void error(String message){
        Alert alert =
                new Alert(Alert.AlertType.ERROR,
                        message,
                        ButtonType.OK);
        alert.setTitle("Alert");
        alert.showAndWait();
    }
    public static void confirmation(String message,String header){
        Alert alert =
                new Alert(Alert.AlertType.CONFIRMATION,
                        message,
                        ButtonType.OK);
        alert.setTitle(header);
        alert.showAndWait();
    }
}
