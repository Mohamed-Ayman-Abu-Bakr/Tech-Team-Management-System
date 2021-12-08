
package Login_Page;

import Classes.Employee;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

import Exceptions.EmptyInputException;
import Exceptions.UserNotFoundException;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void signIn(ActionEvent event) {
        try {
            String email= emailTxtbox.getText();
            String password= passTxtbox.getText();
            if (email.isEmpty() || password.isEmpty()) {
                EmptyInputException E = new EmptyInputException("Please fill all the required fields");
                return;
            }
            if (Employee.login(email, password)!=null) employee_login=Employee.login(email, password);

            else {
                UserNotFoundException e= new UserNotFoundException("User not found \nInvalid email or password");
                return;
            }
            if ((employee_login.getPosition()).equals("Management"))  showManagerScreen(event);
            else showEmployeeScreen(event);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
