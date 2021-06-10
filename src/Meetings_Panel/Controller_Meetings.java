package Meetings_Panel;

import Classes.Meetings;
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

public class Controller_Meetings implements Initializable {
    @FXML
    private Button btn_Update;

    @FXML
    private Button btn_delete;

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
    private ComboBox <String> comb;

    @FXML
    private TextField txt_no;



    ObservableList<Meetings> listM;
    int index = -1;


    public void delete(){
        String id = txt_no.getText();
        Meetings.delete_Meeting(id);
        resetData();
        update();
    }


    public void addMeeting(){
        String title = txt_title.getText();
        String day = valueOf(txt_day.getValue());
        String time = txt_time.getText();
        String department = comb.getSelectionModel().getSelectedItem();

        try {
            Meetings.add_Meeting(title,day,time,department);
            resetData();
            update();
        } catch (EmptyInputException | InvalidDateException | InvalidTimeException e) {
            System.out.println(e);
        }
        resetData();
    }

    public void getSelected (){
        index = table.getSelectionModel().getSelectedIndex();

        if (index <= -1) return;

        txt_title.setText(col_title.getCellData(index));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txt_day.setValue(LocalDate.from(fmt.parse(col_day.getCellData(index))));
        txt_time.setText(col_time.getCellData(index));
        comb.setPromptText(col_type.getCellData(index));
        txt_no.setText(String.valueOf(col_id.getCellData(index)));
    }

    public void edit (){
        String title = txt_title.getText();
        String day = valueOf(txt_day.getValue());
        String time = txt_time.getText();
        String department = comb.getSelectionModel().getSelectedItem();
        String id = txt_no.getText();
        try {
            Meetings.edit_Meeting(title,day,time,department,id);
            update();
            resetData();
        } catch (EmptyInputException | InvalidDateException | InvalidTimeException e) {
            System.out.println(e);
        }

    }

    public void update() {
        col_title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        col_day.setCellValueFactory(new PropertyValueFactory<>("Day"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("Department"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        listM = Meetings.getDataMeetings();
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
        comb.setValue("Development");
        txt_no.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comb.setItems(Meetings.getDepartments());
        resetData();
        update();
        enableButtons();
    }


}
