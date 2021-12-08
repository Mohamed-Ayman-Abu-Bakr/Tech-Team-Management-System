package Manager_Functionalities.Employees_Page.Performance_Stats;

import Classes.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PerformanceStatsController implements Initializable {
    @FXML
    private TableView<Employee> PerformanceTable;

    @FXML
    TableColumn <?,?> Employee_col;
    @FXML
    TableColumn  <?,?> Pos_col;
    @FXML
    TableColumn  <?,?> Tasks_col;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        Pos_col.setCellValueFactory(new PropertyValueFactory<>("position"));
        Employee_col.setCellValueFactory(new PropertyValueFactory<>("tasks"));
    }

    public void Download_table(ActionEvent actionEvent) {
    }
}