package Manager_Functionalities.Projects_Page;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import Classes.Client;
import Classes.Data_Validation;
import Classes.Popup_Window;
import Classes.Project;
import Exceptions.EmptyInputException;
import Exceptions.InvalidCostException;
import Exceptions.InvalidDateException;
import Manager_Functionalities.Projects_Page.TasksForEachProject.ViewTasksController;
import Manager_Functionalities.Projects_Page.singleProjectDetails.Details_Controller;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Login_Page.LoginPageController.manager;


public class Controller_Projects implements Initializable {

    @FXML
    private TextField Cost_input;

    @FXML
    private TableView<Project> projects_table;

    @FXML
    private TableColumn<Project, String> projects_column;

    @FXML
    private TableColumn<Project, Integer> id_column0;
    //textFields
    @FXML
    private TextArea description_input;

    @FXML
    private TextField projectName_input;

    @FXML
    private Button btn_viewTasks;


    //DatePicker

    @FXML
    private DatePicker date_input;
    //Menu Button
    @FXML
    private ComboBox<String> type_input;

    ObservableList<Project> listP;
    public static Client client;

    public void open_client_picker() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PickClientsTable/Client_Pick.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Select Client");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }



    public void AddProject() {
        if(client == null) {
            Popup_Window.error("Please Select a Client");
            return;
        }
        String title=projectName_input.getText();
        String projectDescription=description_input.getText();
        String date= String.valueOf(date_input.getValue());
        String cost=Cost_input.getText();
        try {
            Data_Validation.checkIfNotEmpty(title);
            Data_Validation.checkIfNotEmpty(projectDescription);
            Data_Validation.checkDate(date);
            Data_Validation.checkCost(cost);
            manager.invokeAddProject(new Project(title
                    , projectDescription
                    ,date
                    ,type_input.getValue()
                    ,client
                    ,manager
                    ,Float.parseFloat(cost)));
            UpdateTable();
            resetData();
        } catch (EmptyInputException | InvalidDateException | InvalidCostException e) {
            System.out.println(e);
        }
    }

    public void UpdateTable() {
        projects_column.setCellValueFactory(new PropertyValueFactory<>("projectTitle"));
        id_column0.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        listP = Project.getDataProjects();
        projects_table.setItems(listP);
    }

    public Project getSelected() {
        return projects_table.getSelectionModel().getSelectedItem();
    }

    public void DeleteProject() {
        Project project = projects_table.getSelectionModel().getSelectedItem();
        manager.invokeDeleteProject(project);
        UpdateTable();

    }

    public void showTasksOfSelectedProject() throws IOException {
        Project selected = getSelected();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Manager_Functionalities/Projects_Page/TasksForEachProject/View_Tasks.fxml"));
        ViewTasksController controller = new ViewTasksController();
        controller.initData(selected);
        loader.setController(controller);
        Parent root = loader.load();



        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tasks");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showDetailsOfSelectedProject() {
        TableView<Project> table = projects_table;
        table.setRowFactory(tv -> {
            TableRow<Project> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Project selected = getSelected();
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
        date_input.setValue(LocalDate.now());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("controller initialized");
        type_input.setItems(Project.getProjectTypes());
        resetData();
        UpdateTable();
        showDetailsOfSelectedProject();
        enableButtons();
    }

    public void enableButtons(){
        btn_viewTasks.disableProperty().bind(Bindings.isEmpty(projects_table.getSelectionModel().getSelectedItems()));
    }

    public void ShowStats(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ProjectStats.fxml")));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void DownloadProjects(ActionEvent actionEvent){
        manager.downloadProjects();
    }
}
