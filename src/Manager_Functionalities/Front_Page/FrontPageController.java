/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager_Functionalities.Front_Page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *
 * @author Hanya Adel
 */
public class FrontPageController implements Initializable {
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void open_projects_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Manager_Functionalities/Projects_Page/Projects.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Projects");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void open_clients_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Manager_Functionalities/Clients_Page/Clients.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Clients");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void open_employees_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Employees_Page/Main_Employees_Panel/Employees.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Employees");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void open_meetings_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Manager_Functionalities/Meetings_Page/Meetings.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Meetings");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    public void open_tasks_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Manager_Functionalities/Tasks_Page/Main_Tasks_Page/Main_Tasks_Page.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tasks");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void HandleEditInfo(ActionEvent event) throws IOException {
        Parent page=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EditInfo_Page/EditInfo.fxml")));
        Scene edit= new Scene(page);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }

    @FXML
    private void handleEmails(ActionEvent event) throws Exception {
        Parent FrontPage=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Emails_Page/Emails.fxml")));
        Scene EmailPage= new Scene(FrontPage);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(EmailPage);
        window.show();
    }

    @FXML
    private void signOut(ActionEvent event) throws Exception {
        Parent FrontPage=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Login_Page/LoginPage.fxml")));
        Scene LoginPage= new Scene(FrontPage);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(LoginPage);
        window.show();
    }
    
}
