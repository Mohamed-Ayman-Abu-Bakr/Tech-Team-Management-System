package Manager_Functionalities.Employees_Page.Add_Employees_Panel;

import Classes.Data_Validation;
import Classes.Employee;
import Exceptions.*;
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
        String Ename= name.getText();
        String Eemail=email.getText();
        String Ephone=phone.getText();
        String position=dropdown_position.getValue();
        String Ebirth= birthdate.getValue().toString();
        Alert alert =
                new Alert(Alert.AlertType.CONFIRMATION,
                        "You're about to add an employee with the following properties:" + "\n\n" +
                        "Name: " + Ename + "\n" +
                        "Email: " + Eemail + "\n" +
                        "Phone: " + Ephone + "\n" +
                        "Birthdate: " + Ebirth+ "\n" +
                        "Position: " + position + "\n\n" +
                        "Do you want to continue?",
                        ButtonType.YES,
                        ButtonType.NO);
        alert.setTitle("Add Employee Confirmation");
        Optional<ButtonType> result = alert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.YES){
            try {

                Data_Validation.checkIfNotEmpty(Ename);
                Data_Validation.checkEmail(Eemail);
                Data_Validation.checkNum(Ephone);
                Data_Validation.checkDate(Ebirth);

                Employee.addEmployee(Ename,Eemail,Ephone,Ebirth,position);
                closeStage();
            } catch (InvalidEmailException | InvalidNumberException | InvalidDateException | EmptyInputException e) {
                System.out.println(e);
            }
        }
    }

    public void closeStage () throws IOException {
        Stage stage;
        stage = (Stage) btn_addEmployee.getScene().getWindow();
        stage.close();

        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/Employees_Page/Main_Employees_Panel/Employees.fxml"));
        loader.load();
        Controller_Employees controller = loader.getController();
        controller.updateTable();*/
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
