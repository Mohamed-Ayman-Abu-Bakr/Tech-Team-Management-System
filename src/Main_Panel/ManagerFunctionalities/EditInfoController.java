/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Panel.ManagerFunctionalities;

import Exceptions.*;
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
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static Main_Panel.Login.LoginPageController.employee_login;

/**
 * FXML Controller class
 *
 * @author Hanya Adel
 */
public class EditInfoController implements Initializable {

    @FXML
    private TextField editName;

    @FXML
    private TextField editNum;
    @FXML
    private TextField editEmail;
    @FXML
    private PasswordField editPass;

    @FXML
    private TextField editPos;
    @FXML
    private TextField editUname;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editName.setText(employee_login.getName());
        editName.setEditable(true);
        editEmail.setText(employee_login.getEmail());
        editEmail.setEditable(true);
        editPass.setText(employee_login.getPassword());
        editPass.setEditable(true);
        editPos.setText(employee_login.getPosition());
        editPos.setEditable(false);
        editNum.setText(employee_login.getPhone());
        editNum.setEditable(true);
        editUname.setText(employee_login.getUsername());
        editUname.setEditable(true);        

    }    

    @FXML
    private void handleUpdate() {
        String Name= editName.getText();
        String uName= editUname.getText();
        String Pass= editPass.getText();
        String email= editEmail.getText();
        String number= editNum.getText();
        if (uName.isEmpty() || Pass.isEmpty()|| email.isEmpty() || number.isEmpty()){
            Alert empty=new Alert(Alert.AlertType.ERROR);
            empty.setContentText("Please fill all the required fields");
            empty.setHeaderText("Error");
            empty.showAndWait();            
        }
        else{
            try {
                employee_login.editEmployee_employee(Name,uName,Pass,email,number);
                System.out.println("updated");
                Alert empty=new Alert(Alert.AlertType.INFORMATION);
                empty.setContentText("Your information has been updated successfully");
                empty.setHeaderText("Update");
                empty.showAndWait(); 

            } catch (InvalidNameException | InvalidNumberException | InvalidDateException | InvalidEmailException | EmptyInputException | InvalidPasswordException | InvalidUsernameException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void backToFront(ActionEvent event) throws IOException {
        Parent page=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FrontPage.fxml")));
        Scene edit= new Scene(page);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }
}
