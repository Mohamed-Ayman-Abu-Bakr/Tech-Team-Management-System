/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Panel.ManagerFunctionalities;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Projects_Panel.Controller_Projects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Hanya Adel
 */
public class FrontPageController implements Initializable {
    
    @FXML
    private Label label;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void open_projects_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Projects_Panel/Projects.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Projects");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void open_clients_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Clients_Panel/Clients.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Clients");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void open_employees_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Employees_Panel/Main_Employees_Panel/Employees.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Employees");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void open_meetings_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Meetings_Panel/Meetings.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Meetings");
        stage.setResizable(false);
        stage.show();
    }

    public void open_tasks_panel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tasks_Panel/View_Tasks_Panel/Tasks.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tasks");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void HandleEditInfo(ActionEvent event) throws IOException {
        Parent page=FXMLLoader.load(getClass().getResource("EditInfo.fxml"));
        Scene edit= new Scene(page);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }

    @FXML
    private void handleEmails(ActionEvent event) throws Exception {
        Parent FrontPage=FXMLLoader.load(getClass().getResource("Emails.fxml"));
        Scene EmailPage= new Scene(FrontPage);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(EmailPage);
        window.show();
    }

    @FXML
    private void signOut(ActionEvent event) throws Exception {
        Parent FrontPage=FXMLLoader.load(getClass().getResource("../Login/LoginPage.fxml"));
        Scene LoginPage= new Scene(FrontPage);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(LoginPage);
        window.show();
    }
    
}
