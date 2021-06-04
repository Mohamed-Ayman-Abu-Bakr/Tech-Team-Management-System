package Projects_Panel;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Classes.Clients;
import Classes.Projects;
import MySQL.MySQL_Connector;
import Projects_Panel.singleProjectDetails.Details_Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.sql.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import javax.swing.*;

import static Main_Panel.Login.LoginPageController.employee_login;


public class Controller_Projects implements Initializable {

    @FXML
    private TextField Cost_input;

    @FXML
    private TableView<Projects> projects_table;

    @FXML
    private TableColumn<Projects, String> projects_column;

    @FXML
    private TableColumn<Projects, Integer> id_column0;
    //textfields
    @FXML
    private TextArea description_input;

    @FXML
    private TextField projectName_input;


    //DatePicker

    @FXML
    private DatePicker date_input;
    //Menu Button
    @FXML
    private ComboBox<String> type_input;

    ObservableList<Projects> listP;
    int index = -1;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String CheckedId = null;
    String CheckedManager = null;
    static Clients client;
    StringProperty client_id = new SimpleStringProperty();

    public void open_client_picker() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Client_Pick.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Select Client");
        stage.show();
    }

    /*private void CheckClients() {

        Connection con = MySQL_Connector.ConnectDB();
        try {
            String sql = "select exists(Select * from sql4409579.Clients where id = " + String.valueOf(client.getId())  + ");";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            long i = (long) (rs.getObject(1));
            System.out.println(i);
            if (i == 1) {
                System.out.println("client found " + i);
                CheckedId = String.valueOf(client.getId());
            } else {
                System.out.println("client not found " + i);
                CheckedId = null;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error in checking clients");
        }
    }*/

    /*private void CheckManagers() {


        Connection con = MySQL_Connector.ConnectDB();
        try {
            String sql = "select exists(Select * from sql4409579.employees where id = " + ManagerName_input.getText() + " and position='Management');";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            long i = (long) (rs.getObject(1));
            System.out.println(i);
            if (i == 1) {
                System.out.println("Manager found " + i);
                CheckedManager = ManagerName_input.getText();
            } else {
                System.out.println("Manager not found " + i);
                CheckedManager = null;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error in checking Managers");
        }
    }*/

    /*private void CheckData() {
        CheckClients();
        CheckManagers();
    }*/

    public void AddProject() {
        if(client != null){
            Projects.AddProject(projectName_input.getText()
                    ,description_input.getText()
                    ,String.valueOf(date_input.getValue())
                    ,type_input.getValue()
                    ,client
                    ,String.valueOf(employee_login.getId())
                    ,Cost_input.getText());
            UpdateTable();
            resetData();
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select a client");
        }
    }

    public void UpdateTable() {
        projects_column.setCellValueFactory(new PropertyValueFactory<Projects, String>("projectTitle"));
        id_column0.setCellValueFactory(new PropertyValueFactory<Projects, Integer>("projectId"));
        listP = Projects.getDataProjects();
        projects_table.setItems(listP);
    }

    public Projects getSelected() {
        return projects_table.getSelectionModel().getSelectedItem();
    }

    public void DeleteProject() {
        Projects project = projects_table.getSelectionModel().getSelectedItem();
        project.deleteProject();
        UpdateTable();

    }

    public void SeeInfoProject() {
        TableView<Projects> table = projects_table;
        table.setRowFactory(tv -> {
            TableRow<Projects> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Projects selected = getSelected();
                    try {
                        // passing data to other controller
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("singleProjectDetails/Details_Page.fxml"));
                        Parent root = loader.load();
                        Details_Controller controller = loader.getController();
                        controller.initData(selected);
                        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                        Scene scene = new Scene(root);
                        stage.setTitle("Project Details");
                        stage.setScene(scene);
                        stage.show();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }

    public void resetData(){
        projectName_input.setText("");
        type_input.setValue("Game");
        description_input.setText("");
        Cost_input.setText("");
        date_input.setValue(LocalDate.of(2000,1,1));
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("controller initialized");
        type_input.setItems(Projects.getProjectTypes());
        resetData();
        UpdateTable();
        SeeInfoProject();
    }
}
