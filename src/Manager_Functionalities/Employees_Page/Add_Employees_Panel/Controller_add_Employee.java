package Manager_Functionalities.Employees_Page.Add_Employees_Panel;

import Classes.Employee;
import Exceptions.InvalidDateException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidNumberException;
import Manager_Functionalities.Employees_Page.Main_Employees_Panel.Controller_Employees;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller_add_Employee implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private DatePicker birthdate;

    @FXML
    private Button btn_addEmployee;

    @FXML
    private ComboBox<String> dropdown_position;

    public void add_Employees() throws IOException {

        Alert alert =
                new Alert(Alert.AlertType.CONFIRMATION,
                        "You're about to add an employee with the following properties:" + "\n\n" +
                        "Name: " + name.getText() + "\n" +
                        "Email: " + email.getText() + "\n" +
                        "Phone: " + phone.getText() + "\n" +
                        "Birthdate: " + birthdate.getValue() + "\n" +
                        "Position: " + dropdown_position.getValue() + "\n\n" +
                        "Do you want to continue?",
                        ButtonType.YES,
                        ButtonType.NO);
        alert.setTitle("Delete Employee warning");
        Optional<ButtonType> result = alert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.YES){
            try {
                Employee.addEmployee(name.getText(),email.getText(),phone.getText(),birthdate.getValue().toString(),dropdown_position.getValue());
                closeStage();
            } catch (InvalidNameException | InvalidEmailException | InvalidNumberException | InvalidDateException e) {
                System.out.println(e);
            }
        }
    }

    public void closeStage () throws IOException {
        Stage stage;
        stage = (Stage) btn_addEmployee.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Employees_Page/Main_Employees_Panel/Employees.fxml"));
        loader.load();
        Controller_Employees controller = loader.getController();
        controller.updateTable();
    }

    public void enableButton(){
        boolean isDisabled = (name.getText().trim().isEmpty() || email.getText().trim().isEmpty() || phone.getText().trim().isEmpty() || birthdate.getValue().toString().isEmpty());
        btn_addEmployee.setDisable(isDisabled);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropdown_position.setItems(Employee.getPositions());
        dropdown_position.setValue("Development");
        birthdate.setValue(LocalDate.of(2000,1,1));
    }
}
