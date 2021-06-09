/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Panel.ManagerFunctionalities;

import Email_API.Email;
import Exceptions.InvalidEmailException;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Hanya Adel
 */
public class EmailsController implements Initializable {

    @FXML
    private TextField txtEmailAddress;
    @FXML
    private JFXTextArea txtEmail;
    @FXML
    private TextField txtSubject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleSend(ActionEvent event) {
        try {
            Email.send_Email(txtEmailAddress.getText(),txtSubject.getText(),txtEmail.getText(),"");
            txtEmailAddress.setText("");
            txtSubject.setText("");
            txtEmail.setText("");
        } catch (InvalidEmailException e) {
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BacktoFront(ActionEvent event) throws IOException {
        Parent p=FXMLLoader.load(getClass().getResource("FrontPage.fxml"));
        Scene s= new Scene(p);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
    
}
