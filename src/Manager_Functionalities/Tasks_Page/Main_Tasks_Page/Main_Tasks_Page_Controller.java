package Manager_Functionalities.Tasks_Page.Main_Tasks_Page;

import Classes.Data_Validation;
import Classes.Employee;
import Classes.Task;
import Exceptions.EmptyInputException;
import Exceptions.InvalidDateException;
import Exceptions.TaskAlreadyVerifiedException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import static Login_Page.LoginPageController.manager;
import static Manager_Functionalities.Employees_Page.Main_Employees_Panel.Controller_Employees.employee_selected;


public class Main_Tasks_Page_Controller implements Initializable {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_Update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_verify;

    @FXML
    private ComboBox<Employee> dropdown_employee;

    @FXML
    private TextField txt_task_name;

    @FXML
    private TextArea txt_task_description;

    @FXML
    private DatePicker txt_deadline_date;

    @FXML
    private TableView<Task> table_tasks;

    @FXML
    private TableColumn<Task, Integer> col_task_id;

    @FXML
    private TableColumn<Task, String> col_task_name;

    @FXML
    private TableColumn<Task, String> col_deadline_date;

    @FXML
    private TableColumn<Task, String> col_task_status;

    @FXML
    private TextField filterField;


    Task chosenTask = null; Employee assignedEmployee=null;

    ObservableList<Task> listM =  FXCollections.observableArrayList( new ArrayList<>() );
    ObservableList<Task> dataList;
    ObservableList<Task> temp;

    Employee employee ;

    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void Add_Task() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Manager_Functionalities/Tasks_Page/Add_Task_Page/AddTask.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add Task");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        updateTable();

    }
    @FXML
    void getSelected (MouseEvent event) {

        index = table_tasks.getSelectionModel().getSelectedIndex();
        if(index <= -1) {
            return;
        }
        int taskId=col_task_id.getCellData(index);
        ObservableList<Task> list=Task.getDataTasks();
        ObservableList<Employee> employees= Employee.getDataEmployees();


        for (Task t:list) {
            if (t.getTask_id() == taskId) {
                chosenTask = t;

                break;
            }
        }
        for (Employee e: employees){
            if (e.getId()==chosenTask.getEmployee_id()) {
                assignedEmployee = e;
            }
        }
        dropdown_employee.setItems(employees);
        dropdown_employee.setValue(assignedEmployee);
        txt_task_name.setText(chosenTask.getTask_name());
        txt_task_description.setText(chosenTask.getTask_description());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txt_deadline_date.setValue(LocalDate.from(fmt.parse(col_deadline_date.getCellData(index))));

        enableButtons();
    }

    public void editTask(){
        String new_name=txt_task_name.getText();
        String new_description=txt_task_description.getText();
        String new_deadline=txt_deadline_date.getValue().toString();
        int newEmployeeID=dropdown_employee.getSelectionModel().getSelectedItem().getId();

        try {
            Data_Validation.checkIfNotEmpty(new_name);
            Data_Validation.checkIfNotEmpty(new_description);
            Data_Validation.checkDate(new_deadline);
            manager.invokeEditTask(chosenTask,new_name,new_description,new_deadline,newEmployeeID);
            updateTable();
            Search_Task();
            resetValues();
        } catch (EmptyInputException | InvalidDateException e) {
            System.out.println(e);
        }

    }
    public void Delete_Task() {
        manager.invokeDeleteTask(table_tasks.getSelectionModel().getSelectedItem());
        resetValues();
        updateTable();
    }

    public void updateTable() {
        col_task_id.setCellValueFactory(new PropertyValueFactory<>("task_id"));
        col_task_name.setCellValueFactory(new PropertyValueFactory<>("task_name"));
        col_deadline_date.setCellValueFactory(new PropertyValueFactory<>("deadline_date"));
        col_task_status.setCellValueFactory(new PropertyValueFactory<>("task_status"));

        listM =  FXCollections.observableArrayList( new ArrayList<>() );

        temp= Task.getDataTasks();
        listM.addAll(temp);
        table_tasks.setItems(listM);
        Search_Task();
    }

    void Search_Task() {
        col_task_id.setCellValueFactory(new PropertyValueFactory<>("task_id"));
        col_task_name.setCellValueFactory(new PropertyValueFactory<>("task_name"));
        col_deadline_date.setCellValueFactory(new PropertyValueFactory<>("deadline_date"));
        col_task_status.setCellValueFactory(new PropertyValueFactory<>("task_status"));

        dataList = listM;
        table_tasks.setItems(dataList);
        FilteredList <Task> filteredData = new FilteredList<> (dataList, b -> true);
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

        SortedList<Task> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_tasks.comparatorProperty());
        table_tasks.setItems(sortedData);
    }

    public void resetValues(){
        txt_task_name.setText("");
        dropdown_employee.setItems(null);

        txt_task_description.setText("");
        txt_deadline_date.setValue(LocalDate.now());
        enableButtons();
    }

    public void enableButtons(){
        btn_Update.disableProperty().bind(Bindings.isEmpty(table_tasks.getSelectionModel().getSelectedItems()));
        btn_delete.disableProperty().bind(Bindings.isEmpty(table_tasks.getSelectionModel().getSelectedItems()));
        btn_verify.disableProperty().bind(Bindings.isEmpty(table_tasks.getSelectionModel().getSelectedItems()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employee = employee_selected;
        resetValues();
        updateTable();
        Search_Task();
        enableButtons();
    }

    public void DownloadTasks(ActionEvent actionEvent) {
        manager.downloadTasks();
    }

    @FXML
    void Verify_Task() {

        try {
            chosenTask.verify(assignedEmployee);
            updateTable();
            Search_Task();
            resetValues();
        } catch (TaskAlreadyVerifiedException e) {
            System.out.println(e);
        }

    }

}
