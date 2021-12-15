package Manager_Functionalities.Projects_Page;

import Classes.Project;
import Classes.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewTasksController implements Initializable {

    @FXML
    private TableColumn<Task, String> col_name;

    @FXML
    private TableColumn<Task, String> col_status;

    @FXML
    private TableView<Task> table;


    ObservableList<Task> listM;
    ObservableList<Task> temp;
    Project project;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
    }

    public void update() {
        this.col_name.setCellValueFactory(new PropertyValueFactory<>("task_name"));
        this.col_status.setCellValueFactory(new PropertyValueFactory<>("task_status"));

        listM = FXCollections.observableArrayList( new ArrayList<>());
        temp= Task.getDataTasks();
        for (Task t: temp){
            if (t.getProject_id() == this.project.getProjectId()){
                listM.add(t);
           }
        }

        this.table.setItems(listM);

    }
    public void initData(Project project) {
        this.project = project;
    }
}
