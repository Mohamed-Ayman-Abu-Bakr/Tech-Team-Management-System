package Meetings_Panel;

import Classes.Meetings;
import MySQL.MySQL_Connector;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class Controller_Meetings implements Initializable {
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

    @FXML
    private TextField txt_title;

    @FXML
    private DatePicker txt_day;

    @FXML
    private TextField txt_time;

    @FXML
    private ComboBox  comb;

    @FXML
    private TextField txt_no;



    ObservableList<Meetings> listM;
    int index = -1;

    public Controller_Meetings() {
    }

    public void delete(){
        String id = txt_no.getText();
        Meetings.delete_Meeting(id);
        resetData();
        update();
    }


    public void addMeeting(){
        String title = txt_title.getText();
        String day = (String) valueOf(txt_day.getValue());
        String time = txt_time.getText();
        String department = comb.getSelectionModel().getSelectedItem().toString();
        String id = txt_no.getText();


        Meetings.add_Meeting(title,day,time,department,id);
        resetData();
        update();
    }

    public void getSelected  ( MouseEvent event){
        index = table.getSelectionModel().getSelectedIndex();

        if (index <= -1) return;

        txt_title.setText(col_title.getCellData(index).toString());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txt_day.setValue(LocalDate.from(fmt.parse(col_day.getCellData(index))));
        txt_time.setText(col_time.getCellData(index).toString());
        comb.setPromptText(col_type.getCellData(index).toString());
        txt_no.setText(String.valueOf(col_id.getCellData(index)));
    }

    public void edit (){
        String title = txt_title.getText();
        String day = (String) valueOf(txt_day.getValue());
        String time = txt_time.getText();
        String department = comb.getSelectionModel().getSelectedItem().toString();
        String id = txt_no.getText();
        Meetings.edit_Meeting(title,day,time,department,id);
        update();
        resetData();
    }

    void Select(){
        String s = comb.getSelectionModel().getSelectedItem().toString();
    }

    public void update() {
        col_title.setCellValueFactory(new PropertyValueFactory<Meetings, String>("Title"));
        col_day.setCellValueFactory(new PropertyValueFactory<Meetings, String>("Day"));
        col_time.setCellValueFactory(new PropertyValueFactory<Meetings, String>("Time"));
        col_type.setCellValueFactory(new PropertyValueFactory<Meetings, String>("Department"));
        col_id.setCellValueFactory(new PropertyValueFactory<Meetings, String>("id"));

        listM = Meetings.getDataMeetings();
        table.setItems(listM);
    }

    public void resetData(){
        txt_title.setText("");
        txt_day.setValue(LocalDate.of(2000,1,1));
        txt_time.setText("");
        comb.setValue("Development");
        txt_no.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comb.setItems(Meetings.getDepartments());
        resetData();
        update();
    }


}
