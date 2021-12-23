package Classes;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class Manager extends Employee{

    public Manager(int id, String name, String username, String password, String position,
                   String email, String birthdate, String phone, int completed_tasks) {
        super(id, name, username, password, position, email, birthdate, phone, completed_tasks);
    }


    public void invokeAddTask(){

    }
    public void invokeAddProject(Project p){
        p.AddProject();

    }
    public void invokeUpdateProject(Project p, String title, String description, String date,
                                    String type, Client client, String managerID, String cost) {
        p.setProjectTitle(title);
        p.setProjectDescription(description);
        p.setDateOfDelivery(date);
        p.setType(type);
        p.setCost(Float.parseFloat(cost));
        p.setClient(client);
        p.setManagerID(Integer.parseInt(managerID));
        p.UpdateProject();
    }
    public void downloadProjects(){
        ObservableList<Project> listP= Project.getDataProjects();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Projects Sheet");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");

        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Type");
        header.createCell(3).setCellValue("Description");
        header.createCell(4).setCellValue("Cost");
        header.createCell(5).setCellValue("Client ID");
        header.createCell(6).setCellValue("Delivery Date");

        int idx=1;
        for (Project p: listP){
            XSSFRow row= sheet.createRow(idx);
            row.createCell(0).setCellValue(p.getProjectId());
            row.createCell(1).setCellValue(p.getProjectTitle());
            row.createCell(2).setCellValue(p.getType());
            row.createCell(3).setCellValue(p.getProjectDescription());
            row.createCell(4).setCellValue(p.getCost());
            row.createCell(5).setCellValue(p.getClient_name());
            row.createCell(6).setCellValue(p.getDateOfDelivery());
            idx++;

        }
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop/Projects Sheet.xlsx";
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
    public void invokeAddMeeting(Meeting m){
        m.add_Meeting();
    }
    public void invokeEditMeeting(Meeting m, String title, String day,String time,
                                  String department){

        m.setDay(day);
        m.setDepartment(department);
        m.setTime(time);
        m.setTitle(title);
        m.editMeeting();
    }

}
