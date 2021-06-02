/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Panel.ManagerFunctionalities;

import Email_API.Email;
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

        Email.send_Email(txtEmailAddress.getText(),txtSubject.getText(),txtEmail.getText());
        txtEmailAddress.setText("");
        txtSubject.setText("");
        txtEmail.setText("");
        /*Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = employee_login.getEmail();
        //Your gmail password
        String password = employee_login.getPassword();
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        
        
        try {  
            Message msg = new MimeMessage(session);  
            msg.setFrom(new InternetAddress(myAccountEmail));  
            if (txtEmailAddress.getText().isEmpty()){
                Alert empty=new Alert(Alert.AlertType.ERROR);
                empty.setContentText("Please fill the recipient field");
                empty.setHeaderText("Alert");
                empty.showAndWait();
            }
           msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtEmailAddress.getText()));  
           msg.setSubject(txtSubject.getText());  
            msg.setText( txtEmail.getText());  
            Transport.send(msg);  
            System.out.println("MAIL SENT");  
          } catch (Exception ex) {  
            System.err.println("Cannot send email. " + ex);  
          }      */
          
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
