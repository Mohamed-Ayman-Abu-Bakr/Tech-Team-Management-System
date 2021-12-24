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


    public void invokeAddTask(Task t){
        t.Add_Task();
    }
    public void invokeEditTask(Task t, String new_name, String new_description, String new_deadline, int employeeID){
        t.setTask_name(new_name);
        t.setTask_description(new_description);
        t.setDeadline_date(new_deadline);
        t.setEmployee_id(employeeID);
        t.editTask();
    }
    public void invokeDeleteTask(Task t){
        t.delete_Task();
    }
    public void downloadTasks(){
        ObservableList<Task> list=Task.getDataTasks();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Tasks Sheet");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Employee ID");
        header.createCell(4).setCellValue("Delivery Date");
        header.createCell(5).setCellValue("Status");


        int idx=1;
        for (Task p: list){
            XSSFRow row= sheet.createRow(idx);
            row.createCell(0).setCellValue(p.getTask_id());
            row.createCell(1).setCellValue(p.getTask_name());
            row.createCell(2).setCellValue(p.getTask_description());
            row.createCell(3).setCellValue(p.getEmployee_id());
            row.createCell(4).setCellValue(p.getDeadline_date());
            row.createCell(5).setCellValue(p.getTask_status());
            idx++;

        }
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop/Tasks Sheet.xlsx";

        try {
            FileOutputStream file = new FileOutputStream(desktopPath);
            wb.write(file);
            file.close();
            System.out.println("done");
            Popup_Window.confirmation("The file is Successfully saved in your Desktop","Success");
        } catch (Exception e){
            Popup_Window.error("The file is open by another program. \n Close the file and try again");
        }

    }


    public void invokeAddProject(Project p){
        p.AddProject();

    }
    public void invokeEditProject(Project p, String title, String description, String date,
                                  String type, Client client, String managerID, String cost) {
        p.setProjectTitle(title);
        p.setProjectDescription(description);
        p.setDateOfDelivery(date);
        p.setType(type);
        p.setCost(Float.parseFloat(cost));
        p.setClient(client);
        p.setManagerID(Integer.parseInt(managerID));
        p.editProject();
    }
    public void invokeDeleteProject(Project p){
        p.deleteProject();
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
    public void invokeDeleteMeeting(Meeting m){
        m.delete_Meeting();
    }


    public void invokeDeleteEmployee(Employee e){
        e.deleteEmployee();
    }
    public void resetEmployeePassword(Employee e){
        e.resetPassword();
    }
    public void invokeEditEmployee(Employee e,String name, String email, String phone, String birthdate, String position){
        e.editEmployee(name, email, phone, birthdate,position);
    }
    public void invokeAddEmployee(Employee e){
        e.addEmployee();
    }


    public void invokeDeleteClient(Client c){
        c.delete_Client();
    }
    public void invokeEditClient(Client c, String name, String email, String address,String num){
        c.setName(name);
        c.setEmail(email);
        c.setAddress(address);
        c.setNum(num);
    }
    public void invokesAddClient(Client c){
        c.add_Client();
    }
}
