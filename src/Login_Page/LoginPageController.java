
package Login_Page;

import Classes.Data_Validation;
import Classes.Employee;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

import Classes.Manager;
import Exceptions.EmptyInputException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPasswordException;
import Exceptions.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginPageController implements Initializable {

    @FXML
    private TextField emailTxtbox;
    @FXML
    private PasswordField passTxtbox;


    public static Employee employee_login;
    public static Manager manager;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void signIn(ActionEvent event) {
            String email= emailTxtbox.getText();
            String password= passTxtbox.getText();

            try{
                Data_Validation.checkIfNotEmpty(email);
                Data_Validation.checkPassword(password);

                employee_login=Employee.login(email, password);
                if (employee_login==null) {
                    UserNotFoundException e = new UserNotFoundException();
                    return;
                }
                if ((employee_login.getPosition()).equals("Management")){
                    manager= new Manager(employee_login.getId(), employee_login.getName(),employee_login.getUsername(),
                            employee_login.getPassword(), employee_login.getPosition(), employee_login.getEmail(),
                            employee_login.getBirthdate(),employee_login.getPhone(), employee_login.getCompleted_tasks());
                    showManagerScreen(event);
                }

                else showEmployeeScreen(event);
            }catch (Exception ex){

            }


    }

    public void showManagerScreen(ActionEvent event) throws Exception{
        Parent ReaderLogin=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Manager_Functionalities/Front_Page/FrontPage.fxml")));
        Scene ReaderFunc= new Scene(ReaderLogin);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ReaderFunc);
        window.show();

    }

    public void showEmployeeScreen(ActionEvent event)throws Exception{
        Parent Login=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Employee_Functionalities/Front_Page/FrontPage.fxml")));
        Scene Employee= new Scene(Login);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Employee);
        window.show();
    }
}
