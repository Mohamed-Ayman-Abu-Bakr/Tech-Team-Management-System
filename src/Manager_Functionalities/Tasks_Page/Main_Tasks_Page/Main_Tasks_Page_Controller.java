package Manager_Functionalities.Tasks_Page.Main_Tasks_Page;

import Classes.Employee;
import Classes.Task;
import Exceptions.EmptyInputException;
import Exceptions.InvalidDateException;
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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
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

import static Manager_Functionalities.Employees_Page.Main_Employees_Panel.Controller_Employees.employee_selected;


public class Main_Tasks_Page_Controller implements Initializable {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_Update;

    @FXML
    private Button btn_delete;

    @FXML
    private ComboBox<String> dropdown_employee;

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
    private TableColumn<Task, Date> col_deadline_date;

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


    public void Add_Tasks() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTask.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Select Client");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        Refresh_Tasks();

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
        ObservableList<String> employeeNames= FXCollections.observableArrayList();

        for (Task t:list){
            if (t.getTask_id()==taskId) {
                chosenTask=t;

                break;
            }
        }
        for (Employee e: employees){
            if (e.getId()==chosenTask.getEmployee_id()){
                assignedEmployee=e;
            }
            employeeNames.add(e.getName());
        }

        dropdown_employee.setValue(assignedEmployee.getName());
        txt_task_name.setText(chosenTask.getTask_name());
        txt_task_description.setText(chosenTask.getTask_description());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txt_deadline_date.setValue(LocalDate.from(fmt.parse(col_deadline_date.getCellData(index).toString())));
        dropdown_employee.setItems(employeeNames);
        enableButtons();
    }
    public void Update_Tasks(){
        try {
            Task.update_Task_Manager(txt_task_name.getText(),txt_task_description.getText(),txt_deadline_date.getValue().toString(), dropdown_employee.getSelectionModel().getSelectedItem(), chosenTask.getTask_id());
            Refresh_Tasks();
            Search_Task();
            resetValues();
        } catch (EmptyInputException | InvalidDateException e) {
            System.out.println(e);
        }

    }
    public void Delete_Tasks() {
        Task.delete_Task(String.valueOf(table_tasks.getSelectionModel().getSelectedItem().getTask_id()));
        resetValues();
        Refresh_Tasks();
    }

    public void Refresh_Tasks() {
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

    public void DownloadTasks(ActionEvent actionEvent) {
        listM= Task.getDataTasks();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Tasks Sheet");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Employee ID");
        header.createCell(4).setCellValue("Delivery Date");
        header.createCell(5).setCellValue("Status");


        int idx=1;
        for (Task p: listM){
            XSSFRow row= sheet.createRow(idx);
            row.createCell(0).setCellValue(p.getTask_id());
            row.createCell(1).setCellValue(p.getTask_name());
            row.createCell(2).setCellValue(p.getTask_description());
            row.createCell(3).setCellValue(p.getEmployee_id());
            row.createCell(4).setCellValue(p.getDeadline_date());
            row.createCell(5).setCellValue(p.getTask_status());
            idx++;

        }
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop/Tasks Sheet.xlsx";

        try {
            FileOutputStream file = new FileOutputStream(desktopPath);
            wb.write(file);
            file.close();
            System.out.println("done");
            Alert notFound = new Alert(Alert.AlertType.INFORMATION);
            notFound.setContentText("The file is Successfully saved in your Desktop");
            notFound.setHeaderText("Success");
            notFound.showAndWait();
        } catch (Exception e){
            Alert notFound = new Alert(Alert.AlertType.ERROR);
            notFound.setContentText("The file is open by another program. Please try again");
            notFound.setHeaderText("Error");
            notFound.showAndWait();
        }
    }
}
