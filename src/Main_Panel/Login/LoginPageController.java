
package Main_Panel.Login;

import Classes.Employees;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;
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

//comment
public class LoginPageController implements Initializable {

    @FXML
    private TextField emailTxtbox;
    @FXML
    private PasswordField passTxtbox;


    public static Employees employee_login;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void signIn(ActionEvent event) {
        try {
            String email= emailTxtbox.getText();
            String password= passTxtbox.getText();
            if (email.isEmpty() || password.isEmpty()){
                Alert empty=new Alert(Alert.AlertType.ERROR);
                empty.setContentText("Please fill all the required fields");
                empty.setHeaderText("Alert");
                empty.showAndWait();
                return;
            }
            boolean foundem=false;
            boolean foundpass=false;
            
            ResultSet rs = Employees.getDataEmployee();
            while (rs.next()){
                foundem=false; foundpass=false;
                String em = rs.getString("email");
                if (em.equals(email)) foundem=true;
                String uname= rs.getString("username");
                if (uname.equals(email)) foundem=true;
                String pass= rs.getString("password");
                if (pass.equals(password)) foundpass=true;
                
                if (foundem && foundpass){
                    employee_login = new Employees(Integer.parseInt(rs.getString("id"))
                            ,rs.getString("name")
                            ,rs.getString("username")
                            ,rs.getString("password")
                            ,rs.getString("position")
                            ,rs.getString("email")
                            ,rs.getString("birthdate")
                            ,rs.getString("phone"));
                    break;
                }
            }
            if (!foundem || !foundpass){
                Alert notFound=new Alert(Alert.AlertType.ERROR);
                notFound.setContentText("Your email or password are incorrect");
                notFound.setHeaderText("Alert");
                notFound.showAndWait();
            }
            else{
                if ((employee_login.getPosition()).equals("Management"))  managerScreen(event);
                else employeeScreen(event);
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void managerScreen(ActionEvent event) throws Exception{
        Parent ReaderLogin=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main_Panel/ManagerFunctionalities/FrontPage.fxml")));
                Scene ReaderFunc= new Scene(ReaderLogin);
                Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(ReaderFunc);
                window.show();

    }
    
    public void employeeScreen(ActionEvent event)throws Exception{
        Parent Login=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main_Panel/EmployeeFunctionalities/FrontPage.fxml")));
        Scene Employee= new Scene(Login);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Employee);
        window.show();
    }
}
