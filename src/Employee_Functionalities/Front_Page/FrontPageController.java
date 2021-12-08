
package Employee_Functionalities.Front_Page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
                Parent ReaderLogin=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Meetings.fxml")));
                Scene ReaderFunc= new Scene(ReaderLogin);
                Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(ReaderFunc);
                window.show();
    }

    @FXML
    private void showTasks(ActionEvent event) throws IOException {
        Parent ReaderLogin=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Tasks.fxml")));
        Scene ReaderFunc= new Scene(ReaderLogin);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ReaderFunc);
        window.show();
    }

    @FXML
    private void HandleEditInfo(ActionEvent event) throws IOException {
        Parent page=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EditInfo_Page/EditInfo.fxml")));
        Scene edit= new Scene(page);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }

    @FXML
    private void signOut(ActionEvent event) throws IOException {
        Parent page=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Login_Page/LoginPage.fxml")));
        Scene edit= new Scene(page);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }
}
