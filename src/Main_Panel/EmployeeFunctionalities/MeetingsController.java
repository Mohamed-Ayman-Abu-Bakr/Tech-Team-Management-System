
package Main_Panel.EmployeeFunctionalities;


 
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.Meetings;
import MySQL.MySQL_Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static Main_Panel.Login.LoginPageController.employee_login;

public class MeetingsController implements Initializable {
    @FXML
    private TableView<Meetings> table;
    @FXML
    private TableColumn<Meetings, String> col_title;
    @FXML
    private TableColumn<Meetings, String> col_id;
    @FXML
    private TableColumn<Meetings, String> col_day;
    @FXML
    private TableColumn<Meetings, String> col_time;
    @FXML
    private TableColumn<Meetings, String> col_type;
    private TextField txt_title;
    private DatePicker txt_day;
    private TextField txt_time;
    private TextField txt_type;
    private TextField txt_no;
    ObservableList<Meetings> listM = FXCollections.observableArrayList( new ArrayList<Meetings>() );

    ObservableList<Meetings> temp;
    
    int index = -1;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
 
    @FXML
    public void getSelected(MouseEvent event) {
        this.index = this.table.getSelectionModel().getSelectedIndex();
        if (this.index > -1) {
            this.txt_title.setText(((String)this.col_title.getCellData(this.index)).toString());
            this.txt_day.setPromptText(((String)this.col_day.getCellData(this.index)).toString());
            this.txt_time.setText(((String)this.col_time.getCellData(this.index)).toString());
            this.txt_type.setText(((String)this.col_type.getCellData(this.index)).toString());
            this.txt_no.setText(((String)this.col_id.getCellData(this.index)).toString());
        }
    }
 
 
    public void update() {
            this.col_title.setCellValueFactory(new PropertyValueFactory("Title"));
            this.col_day.setCellValueFactory(new PropertyValueFactory("Day"));
            this.col_time.setCellValueFactory(new PropertyValueFactory("Time"));
            this.col_type.setCellValueFactory(new PropertyValueFactory("Department"));
            this.col_id.setCellValueFactory(new PropertyValueFactory("id"));
            temp= Meetings.getDataMeetings();
            for (Meetings m: temp){
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
        Parent page=FXMLLoader.load(getClass().getResource("FrontPage.fxml"));
        Scene edit= new Scene(page);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }
}