
package Employee_Functionalities.Meetings_Page;

import Classes.Meeting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static Login_Page.LoginPageController.employee_login;

public class MeetingsController implements Initializable {
    @FXML
    private TableView<Meeting> table;
    @FXML
    private TableColumn<Meeting, String> col_title;
    @FXML
    private TableColumn<Meeting, String> col_id;
    @FXML
    private TableColumn<Meeting, String> col_day;
    @FXML
    private TableColumn<Meeting, String> col_time;
    @FXML
    private TableColumn<Meeting, String> col_type;

    ObservableList<Meeting> listM = FXCollections.observableArrayList( new ArrayList<>() );

    ObservableList<Meeting> temp;

    public MeetingsController() {
    }

    public void update() {
            this.col_title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            this.col_day.setCellValueFactory(new PropertyValueFactory<>("Day"));
            this.col_time.setCellValueFactory(new PropertyValueFactory<>("Time"));
            this.col_type.setCellValueFactory(new PropertyValueFactory<>("Department"));
            this.col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            temp= Meeting.getDataMeetings();
            for (Meeting m: temp){
                if ((m.getDepartment()).equals(employee_login.getPosition())){
                    listM.add(m);
                }
            }

            this.table.setItems(listM);

    }
 
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.update();
    }

    @FXML
    private void BackToFront(ActionEvent event) throws IOException {
        Parent page=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Employee_Functionalities/Front_Page/FrontPage.fxml")));
        Scene edit= new Scene(page);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }
}