package Employees_Panel.Main_Employees_Panel;

import Employees_Panel.Edit_Employees_Panel.Controller_edit_Employee;
import Classes.Employees;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller_Employees implements Initializable {

    @FXML
    private TextField search_field;

    @FXML
    private TableView<Employees> table_employees;

    @FXML
    private TableColumn<Employees, String> col_name;

    @FXML
    private TableColumn<Employees, String > col_position;

    @FXML
    private TableColumn<Employees, String> col_email;

    @FXML
    private TableColumn<Employees, String> col_birthdate;

    @FXML
    private TableColumn<Employees, String> col_phone;


    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Button reset_password;

    @FXML
    private Button tasks;



    ObservableList<Employees> listM;
    ObservableList<Employees> dataList;

    public static Employees employee_selected;

    public void refresh(){
        updateTable();
        search_Name();
    }


    public void updateTable(){
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));


        listM = Employees.getDataEmployees();

        ///System.out.println("Updated");

        table_employees.setItems(listM);
    }
    public void delete(){
        Alert alert =
                new Alert(Alert.AlertType.WARNING,
                        "Are you sure that you want to delete this Employee",
                        ButtonType.YES,
                        ButtonType.NO);
        alert.setTitle("Delete Employee warning");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get()==ButtonType.YES){
                Employees employee = table_employees.getSelectionModel().getSelectedItem();
                employee.deleteEmployee();
                updateTable();
        }
    }

    public void reset_Password(){
        Alert alert =
                new Alert(Alert.AlertType.WARNING,
                        "Are you sure that you want to reset this Employee's Password?",
                        ButtonType.YES,
                        ButtonType.NO);
        alert.setTitle("Reset Password warning");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get()==ButtonType.YES) {
            Employees employee = table_employees.getSelectionModel().getSelectedItem();
            employee.resetPassword();
        }
    }

    @FXML
    public void search_Name(){

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        dataList = Employees.getDataEmployees();
        table_employees.setItems(dataList);
        FilteredList<Employees> filteredData = new FilteredList<>(dataList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search_field.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Employee -> {
            // If filter text is empty, display all persons.

            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if (Employee.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches first name.
            } else if (Employee.getPosition().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            }
            else if (Employee.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            }
            else if (String.valueOf(Employee.getBirthdate()).contains(lowerCaseFilter)){
                return true;
            }
            else if (Employee.getPhone().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            }
            else {
                return false; // Does not match.
            }
        }));

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Employees> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table_employees.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table_employees.setItems(sortedData);
    }

    @FXML
    public void open_add_employee_window() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Add_Employees_Panel/add_Employee.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add New Employee");
        stage.show();
    }

    @FXML
    public void open_edit_employee_window() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Edit_Employees_Panel/edit_Employee.fxml"));
        Parent root = loader.load();
        Controller_edit_Employee controller = loader.getController();
        controller.setValues(table_employees.getSelectionModel().getSelectedItem());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Edit Employee");
        stage.show();
    }

    @FXML
    public void open_tasks_window() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tasks_Panel/Add_Tasks_Panel/Add_Tasks.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tasks");
        stage.show();
    }


    public void control_buttons(){
        edit.disableProperty().bind(Bindings.isEmpty(table_employees.getSelectionModel().getSelectedItems()));
        delete.disableProperty().bind(Bindings.isEmpty(table_employees.getSelectionModel().getSelectedItems()));
        reset_password.disableProperty().bind(Bindings.isEmpty(table_employees.getSelectionModel().getSelectedItems()));
        tasks.disableProperty().bind(Bindings.isEmpty(table_employees.getSelectionModel().getSelectedItems()));
    }

    @FXML
    void getSelected (MouseEvent event) {
        employee_selected = table_employees.getSelectionModel().getSelectedItem();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        search_Name();
        control_buttons();
    }
}
