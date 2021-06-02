
package Main_Panel.EmployeeFunctionalities;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FrontPageController implements Initializable {



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



    @FXML
    private void showMeetings(ActionEvent event) throws IOException {
                Parent ReaderLogin=FXMLLoader.load(getClass().getResource("Meetings.fxml"));
                Scene ReaderFunc= new Scene(ReaderLogin);
                Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(ReaderFunc);
                window.show();
    }

    @FXML
    private void showTasks(ActionEvent event) throws IOException {
        Parent ReaderLogin=FXMLLoader.load(getClass().getResource("Tasks.fxml"));
        Scene ReaderFunc= new Scene(ReaderLogin);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ReaderFunc);
        window.show();
    }

    @FXML
    private void HandleEditInfo(ActionEvent event) throws IOException {
        Parent page=FXMLLoader.load(getClass().getResource("EditInfo.fxml"));
        Scene edit= new Scene(page);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }
    
}
