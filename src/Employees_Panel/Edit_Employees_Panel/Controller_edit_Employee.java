package Employees_Panel.Edit_Employees_Panel;

import Employees_Panel.Main_Employees_Panel.Controller_Employees;
import Classes.Employees;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller_edit_Employee implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private DatePicker birthdate;

    @FXML
    private Button btn_editEmployee;

    @FXML
    private ComboBox<String> dropdown_position;

    private Employees employee;

    public void setValues(Employees employee) {
        this.employee= employee;
        this.name.setText(employee.getName());
        this.email.setText(employee.getEmail());
        this.phone.setText(employee.getPhone());

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthdate.setValue(LocalDate.from(fmt.parse(employee.getBirthdate())));

        dropdown_position.setValue(employee.getPosition());
    }

    public void editEmployee() throws IOException {
        Alert alert =
                new Alert(Alert.AlertType.CONFIRMATION,
                        "The new Details for the Employee are:" + "\n\n" +
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

        if (result.get() == ButtonType.YES) {
            employee.editEmployee_manager(name.getText(), email.getText(), phone.getText(), birthdate.getValue().toString(), dropdown_position.getValue());
            closeStage();
        }
    }

    public void closeStage () throws IOException {
        Stage stage;
        stage = (Stage) btn_editEmployee.getScene().getWindow();
        stage.close();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Employees_Panel/Main_Employees_Panel/Employees.fxml"));
        loader.load();
        Controller_Employees controller = loader.getController();
        controller.updateTable();


    }
    public void enableButton(){
        boolean isDisabled = (name.getText().trim().isEmpty() || email.getText().trim().isEmpty() || phone.getText().trim().isEmpty() || birthdate.getValue().toString().isEmpty() || dropdown_position.getValue() == employee.getPosition() || birthdate.getValue().toString() == employee.getBirthdate());
        btn_editEmployee.setDisable(isDisabled);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropdown_position.setItems(Employees.getPositions());
    }
}
