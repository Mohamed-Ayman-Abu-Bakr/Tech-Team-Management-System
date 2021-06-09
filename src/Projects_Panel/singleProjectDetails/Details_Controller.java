package Projects_Panel.singleProjectDetails;

import Classes.Clients;
import Classes.Projects;
import Exceptions.EmptyInputException;
import Exceptions.InvalidCostException;
import Exceptions.InvalidDateException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static Main_Panel.Login.LoginPageController.employee_login;

public class Details_Controller implements Initializable {
    @FXML
    private AnchorPane Type_details;

    @FXML
    private Button backToProjects_button;

    @FXML
    private TextArea Project_describtion_details;

    @FXML
    private TextField project_title_details;

    @FXML
    private TextField client_id_details;

    @FXML
    private TextField Managers_id_details;

    @FXML
    private TextField cost_details;

    @FXML
    private DatePicker DatePicker_Details;

    @FXML
    private ComboBox <String> type_input;

    private Projects project;
    private Clients client = new Clients(0,"name","num","email","address");


    @FXML
    public void backToProjectsPanel(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Projects.fxml"));
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Projects panel");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("back to projects panel failed");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    int index = -1;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String CheckedId = null;
    String CheckedManager = null;
    int ProjectId ;

    public void UpdateProject() {
        try {
            project.UpdateProject(project_title_details.getText()
                            ,Project_describtion_details.getText()
                            ,String.valueOf(DatePicker_Details.getValue())
                            ,type_input.getValue()
                            ,client
                            ,String.valueOf(employee_login.getId())
                            ,cost_details.getText());
        } catch (EmptyInputException e) {
        } catch (InvalidDateException e) {
        } catch (InvalidCostException e) {
        }
    }

    public void initData(Projects project) {
        this.project = project;
        Clients.getClient_from_id(project.getClient_name(),client);
        client_id_details.setText(String.valueOf(project.getClient_name()));
        cost_details.setText(String.valueOf(project.getCost()));
        project_title_details.setText(project.getProjectTitle());
        Project_describtion_details.setText(project.getProjectDescription());
        Managers_id_details.setText(String.valueOf(project.getManager()));
        LocalDate localDate = LocalDate.parse(project.getDateOfDelivery());
        DatePicker_Details.setValue(localDate);
        ProjectId=project.getProjectId();

        type_input.setItems(Projects.getProjectTypes());
        type_input.setValue(project.getType());
    }

}
