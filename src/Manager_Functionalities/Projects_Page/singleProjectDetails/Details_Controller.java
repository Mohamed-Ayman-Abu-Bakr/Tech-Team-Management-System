package Manager_Functionalities.Projects_Page.singleProjectDetails;

import Classes.Client;
import Classes.Data_Validation;
import Classes.Project;
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
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import static Login_Page.LoginPageController.manager;

public class Details_Controller implements Initializable {

    @FXML
    private TextArea Project_description_details;

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

    private Project project;
    private final Client client = new Client(0,"name","num","email","address");


    @FXML
    public void backToProjectsPanel(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Projects.fxml")));
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

    public void editProject() {
        String title=project_title_details.getText();
        String description=Project_description_details.getText();
        String Date=String.valueOf(DatePicker_Details.getValue());
        String type=type_input.getValue();
        String cost=cost_details.getText();
        String managerId=String.valueOf(manager.getId());

        try {
            Data_Validation.checkIfNotEmpty(title);
            Data_Validation.checkIfNotEmpty(description);
            Data_Validation.checkDate(Date);
            Data_Validation.checkCost(cost);
            Data_Validation.checkIfNotEmpty(type);
            Data_Validation.checkIfNotEmpty(managerId);

            manager.invokeEditProject(project, title
                            , description
                            ,Date
                            ,type
                            ,client
                            ,managerId
                            ,cost);
        } catch (EmptyInputException | InvalidDateException | InvalidCostException e) {
            System.out.println(e);
        }
    }

    public void initData(Project project) {
        this.project = project;
        Client.getClient_from_id(project.getClient_name(),client);
        client_id_details.setText(String.valueOf(project.getClient_name()));
        cost_details.setText(String.valueOf(project.getCost()));
        project_title_details.setText(project.getProjectTitle());
        Project_description_details.setText(project.getProjectDescription());
        Managers_id_details.setText(String.valueOf(project.getManager()));
        LocalDate localDate = LocalDate.parse(project.getDateOfDelivery());
        DatePicker_Details.setValue(localDate);
        ProjectId=project.getProjectId();

        type_input.setItems(Project.getProjectTypes());
        type_input.setValue(project.getType());
    }

}
