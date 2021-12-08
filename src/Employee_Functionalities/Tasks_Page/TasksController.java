
package Employee_Functionalities.Tasks_Page;


import Classes.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static Login_Page.LoginPageController.employee_login;

public class TasksController implements Initializable {
    @FXML
    private TableView<Task> table;

    @FXML
    private TableColumn<Task, String> col_name;

    @FXML
    private TableColumn<Task, String> col_description;

    @FXML
    private TableColumn<Task, String> col_deadline;

    @FXML
    private TableColumn<Task, String> col_status;

    @FXML
    private ComboBox <String> status;

    @FXML
    private Button btn_update;


    ObservableList<Task> listM = FXCollections.observableArrayList( new ArrayList<>() );

    ObservableList<Task> temp;

    Task task;

    int index = -1;
 
    @FXML
    public void getSelected() {
        this.index = this.table.getSelectionModel().getSelectedIndex();
        task = this.table.getSelectionModel().getSelectedItem();
        btn_update.setDisable(true);
        if (this.index > -1) {
            this.status.setValue(task.getTask_status());
            btn_update.setDisable(false);
        }
    }
 
 
    public void update() {
            this.col_name.setCellValueFactory(new PropertyValueFactory<>("task_name"));
            this.col_description.setCellValueFactory(new PropertyValueFactory<>("task_description"));
            this.col_deadline.setCellValueFactory(new PropertyValueFactory<>("deadline_date"));
            this.col_status.setCellValueFactory(new PropertyValueFactory<>("task_status"));

            listM = FXCollections.observableArrayList( new ArrayList<>());
            temp= Task.getDataTasks();
            for (Task t: temp){
                if (t.getEmployee_id() == employee_login.getId()){
                    listM.add(t);
                }
            }

            this.table.setItems(listM);

    }

    public void updateStatus(){
        task.update_Task_status(status.getValue());
        update();
    }
 
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.update();
        status.setItems(Task.getTasks_Status());
    }

    @FXML
    private void BackToFront(ActionEvent event) throws IOException {
        Parent page=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Front_Page/FrontPage.fxml")));
        Scene edit= new Scene(page);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }
}