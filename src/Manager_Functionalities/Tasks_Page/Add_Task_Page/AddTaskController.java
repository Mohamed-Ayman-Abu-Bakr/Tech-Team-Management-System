package Manager_Functionalities.Tasks_Page.Add_Task_Page;

import Classes.Employee;
import Classes.Project;
import Classes.Task;
import Exceptions.EmptyInputException;
import Exceptions.InvalidDateException;
import Manager_Functionalities.Tasks_Page.Main_Tasks_Page.Main_Tasks_Page_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {
    @FXML
    private Button btn_add;

    @FXML
    private TextField txt_task_name;

    @FXML
    private TextArea txt_task_description;

    @FXML
    private DatePicker txt_deadline_date;

    @FXML
    private TableView<Employee> employees_table;

    @FXML
    private TableColumn<Employee, Integer> col_employee_ID;

    @FXML
    private TableColumn<Employee, String> col_employee_name;

    @FXML
    private ComboBox<String> dropdown_project;


    ObservableList<Employee> employees;

    public void updateTable(){
        employees_table.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        col_employee_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_employee_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        employees= Employee.getDataEmployees();
        employees_table.setItems(employees);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        ObservableList<Project> list=Project.getDataProjects();
        ObservableList<String> projectNames=FXCollections.observableArrayList();
        for (Project p: list){
            projectNames.add(p.getProjectTitle());
        }
        dropdown_project.setItems(projectNames);

    }

    public void add_task(ActionEvent actionEvent) throws InvalidDateException, EmptyInputException, IOException {
        String task_name= txt_task_name.getText();
        String task_description= txt_task_description.getText();
        String deadline= txt_deadline_date.getValue().toString();
        int employeeID= employees_table.getSelectionModel().getSelectedItem().getId();
        String projectName= dropdown_project.getSelectionModel().getSelectedItem();
        int projectID=0;

        ObservableList<Project> list= FXCollections.observableArrayList();
        list= Project.getDataProjects();
        for (Project p:list){
            if ((p.getProjectTitle()).equals(projectName)){

                projectID=p.getProjectId();
                break;
            }
        }
        Task.Add_Task(employeeID,projectID,task_name,task_description,deadline);
        closeStage();
    }

    public void closeStage () throws IOException {
        Stage stage;
        stage = (Stage) btn_add.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Manager_Functionalities/Tasks_Page/Main_Tasks_Page/Main_Tasks_Page.fxml"));
        loader.load();
        Main_Tasks_Page_Controller controller = loader.getController();
    }
}
