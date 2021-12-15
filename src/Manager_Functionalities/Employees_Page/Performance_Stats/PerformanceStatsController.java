package Manager_Functionalities.Employees_Page.Performance_Stats;

import Classes.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class PerformanceStatsController implements Initializable {
    @FXML
    private TableView<Employee> PerformanceTable;
    @FXML
    TableColumn <Employee,String> Employee_col;
    @FXML
    TableColumn  <Employee,String> Pos_col;
    @FXML
    TableColumn  <Employee,String> Tasks_col;

    ObservableList<Employee> listM;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }

    public void updateTable(){
        Employee_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        Pos_col.setCellValueFactory(new PropertyValueFactory<>("position"));
        Tasks_col.setCellValueFactory(new PropertyValueFactory<>("completed_tasks"));

        listM = Employee.getDataEmployees();
        PerformanceTable.setItems(listM);
    }

    public void Download_table(ActionEvent actionEvent) {
        listM= Employee.getDataEmployees();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Employee Performance Stats Sheet");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Position");
        header.createCell(3).setCellValue("Completed Tasks");

        int idx=1;
        for (Employee e: listM){
            XSSFRow row= sheet.createRow(idx);
            row.createCell(0).setCellValue(e.getId());
            row.createCell(1).setCellValue(e.getName());
            row.createCell(2).setCellValue(e.getPosition());
            row.createCell(3).setCellValue(e.getCompleted_tasks());
            idx++;

        }
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop/Employee Performance Stats Sheet.xlsx";
        try {
            FileOutputStream file = new FileOutputStream(desktopPath);
            wb.write(file);
            file.close();
            System.out.println("done");
            Alert notFound = new Alert(Alert.AlertType.INFORMATION);
            notFound.setContentText("The file is Successfully saved in your Desktop");
            notFound.setHeaderText("Success");
            notFound.showAndWait();
        } catch (Exception e){
            Alert notFound = new Alert(Alert.AlertType.ERROR);
            notFound.setContentText("The file is open by another program. Please try again");
            notFound.setHeaderText("Error");
            notFound.showAndWait();
        }

    }
}