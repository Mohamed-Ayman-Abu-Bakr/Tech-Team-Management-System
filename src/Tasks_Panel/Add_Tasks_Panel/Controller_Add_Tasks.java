package Tasks_Panel.Add_Tasks_Panel;

import Classes.Employees;
import Classes.Tasks;
import Exceptions.EmptyInputException;
import Exceptions.InvalidDateException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static Employees_Panel.Main_Employees_Panel.Controller_Employees.employee_selected;


public class Controller_Add_Tasks implements Initializable {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_Update;

    @FXML
    private Button btn_delete;


    @FXML
    private TextField txt_task_id;

    @FXML
    private TextField txt_employee_id;

    @FXML
    private TextField txt_task_name;

    @FXML
    private TextField txt_task_description;

    @FXML
    private DatePicker txt_deadline_date;
    
    @FXML
    private TableView<Tasks> table_tasks;

    @FXML
    private TableColumn<Tasks, Integer> col_task_id;

    @FXML
    private TableColumn<Tasks, Integer> col_employee_id;

    @FXML
    private TableColumn<Tasks, String> col_task_name;

    @FXML
    private TableColumn<Tasks, String> col_task_description;

    @FXML
    private TableColumn<Tasks, Date> col_deadline_date;

    @FXML
    private TableColumn<Tasks, String> col_task_status;
    
    @FXML
    private TextField filterField;

  
    

    ObservableList<Tasks> listM =  FXCollections.observableArrayList( new ArrayList<>() );
    ObservableList<Tasks> dataList;
    ObservableList<Tasks> temp;

    Employees employee ;
    
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
            
     public void Add_Tasks(){
         try {
             Tasks.Add_Task(txt_employee_id.getText(),txt_task_name.getText(),txt_task_description.getText(),txt_deadline_date.getValue().toString());
             Refresh_Tasks();
             Search_Task();
             resetValues();
         } catch (EmptyInputException | InvalidDateException e) {
             System.out.println(e);
         }

     }
    @FXML
     void getSelected (MouseEvent event) {

         index = table_tasks.getSelectionModel().getSelectedIndex();
         if(index <= -1) {
             return;
         }
         txt_task_id.setText(col_task_id.getCellData(index).toString());
         txt_employee_id.setText(col_employee_id.getCellData(index).toString());
         txt_task_name.setText(col_task_name.getCellData(index));
         txt_task_description.setText(col_task_description.getCellData(index));
         DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         txt_deadline_date.setValue(LocalDate.from(fmt.parse(col_deadline_date.getCellData(index).toString())));
        enableButtons();
     }
     public void Update_Tasks(){
         try {
             Tasks.update_Task_Manager(txt_task_name.getText(),txt_task_description.getText(),txt_deadline_date.getValue().toString(),txt_task_id.getText());
             Refresh_Tasks();
             Search_Task();
             resetValues();
         } catch (EmptyInputException | InvalidDateException e) {
             System.out.println(e);
         }

     }
     public void Delete_Tasks() {
         Tasks.delete_Task(txt_task_id.getText());
         resetValues();
         Refresh_Tasks();
     }
     
     public void Refresh_Tasks() {
        col_task_id.setCellValueFactory(new PropertyValueFactory<>("task_id"));
        col_employee_id.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        col_task_name.setCellValueFactory(new PropertyValueFactory<>("task_name"));
        col_task_description.setCellValueFactory(new PropertyValueFactory<>("task_description"));
        col_deadline_date.setCellValueFactory(new PropertyValueFactory<>("deadline_date"));
        col_task_status.setCellValueFactory(new PropertyValueFactory<>("task_status"));

         listM =  FXCollections.observableArrayList( new ArrayList<>() );

        temp= Tasks.getDataTasks();
         for (Tasks t: temp){
             if ((t.getEmployee_id())==(employee.getId())){
                 listM.add(t);
             }
         }
        
        //listM = Tasks.getDataTasks();
        table_tasks.setItems(listM);
     }
     
     void Search_Task() {
        col_task_id.setCellValueFactory(new PropertyValueFactory<>("task_id"));
        col_employee_id.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        col_task_name.setCellValueFactory(new PropertyValueFactory<>("task_name"));
        col_task_description.setCellValueFactory(new PropertyValueFactory<>("task_description"));
        col_deadline_date.setCellValueFactory(new PropertyValueFactory<>("deadline_date"));
        col_task_status.setCellValueFactory(new PropertyValueFactory<>("task_status"));
        
        dataList = listM;
        table_tasks.setItems(dataList);
        FilteredList <Tasks> filteredData = new FilteredList<> (dataList, b -> true);
        filterField.textProperty().addListener((observable,oldValue,newValue) -> filteredData.setPredicate(task ->{
            if(newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            if (task.getTask_name().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }  else if(task.getTask_description().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            else if (String.valueOf(task.getTask_status()).contains(lowerCaseFilter)) {
                return true;
            }
            else if (String.valueOf(task.getDeadline_date()).contains(lowerCaseFilter)) {
                return true;
            }

            else
                return false;

        }));
        
        SortedList<Tasks> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_tasks.comparatorProperty());
        table_tasks.setItems(sortedData);
    }
     
    public void resetValues(){
        txt_task_id.setText("");
        txt_task_name.setText("");
        txt_employee_id.setText(String.valueOf(employee.getId()));
        txt_task_description.setText("");
        txt_deadline_date.setValue(LocalDate.now());
        enableButtons();
    }

    public void enableButtons(){
        btn_Update.disableProperty().bind(Bindings.isEmpty(table_tasks.getSelectionModel().getSelectedItems()));
        btn_delete.disableProperty().bind(Bindings.isEmpty(table_tasks.getSelectionModel().getSelectedItems()));
    }

     @Override
    public void initialize(URL url, ResourceBundle rb) {
         employee = employee_selected;
       resetValues();
       Refresh_Tasks();
       Search_Task();
       enableButtons();
    }    
}
