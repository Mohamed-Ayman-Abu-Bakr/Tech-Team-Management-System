package Manager_Functionalities.Employees_Page.Edit_Employees_Panel;

import Classes.Data_Validation;
import Classes.Employee;
import Exceptions.EmptyInputException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidNumberException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import static Login_Page.LoginPageController.manager;
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

    private Employee employee;

    public void setValues(Employee employee) {
        this.employee= employee;
        this.name.setText(employee.getName());
        this.email.setText(employee.getEmail());
        this.phone.setText(employee.getPhone());

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthdate.setValue(LocalDate.from(fmt.parse(employee.getBirthdate())));

        dropdown_position.setValue(employee.getPosition());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void editEmployee() throws IOException {
        String newName=name.getText();
        String newEmail=email.getText();
        String newPhone=phone.getText();
        String newBirth=birthdate.getValue().toString();
        String newPos= dropdown_position.getValue();
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
        alert.setTitle("Edit Employee confirmation");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES) {
            try {
                Data_Validation.checkIfNotEmpty(newName);
                Data_Validation.checkEmail(newEmail);
                Data_Validation.checkNum(newPhone);
                Data_Validation.checkDate(newBirth);
                manager.invokeEditEmployee(employee,name.getText(), email.getText(), phone.getText(), birthdate.getValue().toString(), dropdown_position.getValue());
                closeStage();
            } catch (InvalidEmailException | InvalidNumberException | InvalidDateException | EmptyInputException e) {
                System.out.println(e);
            }

        }
    }

    public void closeStage () throws IOException {
        Stage stage;
        stage = (Stage) btn_editEmployee.getScene().getWindow();
        stage.close();


    }
    public void enableButton(){
        boolean isDisabled = (name.getText().trim().isEmpty() || email.getText().trim().isEmpty() || phone.getText().trim().isEmpty() || birthdate.getValue().toString().isEmpty());
        btn_editEmployee.setDisable(isDisabled);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropdown_position.setItems(Employee.getPositions());
    }
}
