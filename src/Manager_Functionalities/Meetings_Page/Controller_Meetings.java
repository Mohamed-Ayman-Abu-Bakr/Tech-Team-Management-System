package Manager_Functionalities.Meetings_Page;

import Classes.Data_Validation;
import Classes.Employee;
import Classes.Meeting;
import Exceptions.EmptyInputException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidTimeException;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import static java.lang.String.valueOf;
import static Login_Page.LoginPageController.manager;

public class Controller_Meetings implements Initializable {
    @FXML
    private Button btn_Update;

    @FXML
    private Button btn_delete;

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

    @FXML
    private TextField txt_title;

    @FXML
    private DatePicker txt_day;

    @FXML
    private TextField txt_time;

    @FXML
    private ComboBox <String> comb;

    @FXML
    private TextField txt_no;



    ObservableList<Meeting> listM;
    int index = -1;


    public void deleteMeeting(){
        manager.invokeDeleteMeeting(getSelected());
        resetData();
        updateTable();
    }


    public void addMeeting(){
        String title = txt_title.getText();
        String day = valueOf(txt_day.getValue());
        String time = txt_time.getText();
        String department = comb.getSelectionModel().getSelectedItem();

        try {
            Data_Validation.checkIfNotEmpty(title);
            Data_Validation.checkDate(day);
            Data_Validation.checkTime(time);

            manager.invokeAddMeeting(new Meeting(title,day, time, department));
            resetData();
            updateTable();
        } catch (EmptyInputException | InvalidDateException | InvalidTimeException e) {
            System.out.println(e);
        }
        resetData();
    }

    public Meeting getSelected (){
        index = table.getSelectionModel().getSelectedIndex();

        if (index <= -1) return null;

        txt_title.setText(col_title.getCellData(index));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txt_day.setValue(LocalDate.from(fmt.parse(col_day.getCellData(index))));
        txt_time.setText(col_time.getCellData(index));
        comb.setValue(col_type.getCellData(index));
        txt_no.setText(String.valueOf(col_id.getCellData(index)));
        return table.getSelectionModel().getSelectedItem();
    }

    public void editMeeting(){
        String title = txt_title.getText();
        String day = valueOf(txt_day.getValue());
        String time = txt_time.getText();
        String department = comb.getSelectionModel().getSelectedItem();
        String id = txt_no.getText();
        try {
            Data_Validation.checkIfNotEmpty(title);
            Data_Validation.checkDate(day);
            Data_Validation.checkTime(time);

            Meeting m= getSelected();
            manager.invokeEditMeeting(m, title,day,time,department);
            updateTable();
            resetData();
        } catch (EmptyInputException | InvalidDateException | InvalidTimeException e) {
            System.out.println(e);
        }

    }

    public void updateTable() {
        col_title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        col_day.setCellValueFactory(new PropertyValueFactory<>("Day"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("Department"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        listM = Meeting.getDataMeetings();
        table.setItems(listM);
    }

    public void enableButtons(){
        btn_Update.disableProperty().bind(Bindings.isEmpty(table.getSelectionModel().getSelectedItems()));
        btn_delete.disableProperty().bind(Bindings.isEmpty(table.getSelectionModel().getSelectedItems()));
    }

    public void resetData(){
        txt_title.setText("");
        LocalDate.now();
        txt_day.setValue(LocalDate.now());
        txt_time.setText("");
        comb.setValue("");
        txt_no.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comb.setItems(Employee.getPositions());
        resetData();
        updateTable();
        enableButtons();
    }


}
