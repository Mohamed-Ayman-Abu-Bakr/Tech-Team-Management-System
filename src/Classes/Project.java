package Classes;

import Email_API.Email;
import Exceptions.EmptyInputException;
import Exceptions.InvalidCostException;
import Exceptions.InvalidDateException;
import MySQL.MySQL_Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class Project {

    private final int projectId;
    private final String projectTitle;
    private final String dateOfDelivery;
    private final String projectDescription;
    private final int client_id;
    private String Type;
    private final int Manager;
    private final float cost;


    public Project(int projectId,
                   String projectTitle,
                   String projectDescription,
                   String dateOfDelivery,
                   String Type,
                   int client_id,
                   int Manager,
                   float cost) {
        this.projectId = projectId;
        this.client_id = client_id;
        this.projectTitle = projectTitle;
        this.dateOfDelivery = dateOfDelivery;
        this.projectDescription = projectDescription;
        this.Type = Type;
        this.Manager = Manager;
        this.cost = cost;
    }



    public void setType(String Type) {
        this.Type = Type;
    }


    public int getProjectId() {
        return projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public int getClient_name() {
        return this.client_id;
    }

    public String getType() {
        return this.Type;
    }

    public int getManager() {
        return Manager;
    }

    public float getCost() {
        return cost;
    }

    private static final ObservableList<String> projectTypes = FXCollections.observableArrayList("Game", "Mobile App", "Desktop App", "Web App", "Embedded Sys. App", "Data analysis");

    public static ObservableList<String> getProjectTypes() {
        return projectTypes;
    }

    public static ObservableList<Project> getDataProjects(){
        Connection con = MySQL_Connector.ConnectDB();
        ObservableList<Project> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = Objects.requireNonNull(con).prepareStatement("SELECT * FROM Projects");
            ResultSet rs = ps.executeQuery();
            System.out.println("getDataProjects success");

            while(rs.next()){
                list.add(new Project(Integer.parseInt(

                                rs.getString("id"))
                                ,rs.getString("title")
                                ,rs.getString("projectDescription")
                                ,rs.getString("date")
                                ,rs.getString("type")
                                ,rs.getInt("client_name")
                                ,rs.getInt("Manager_name")
                                ,rs.getFloat("cost")
                        )

                );
            }
        }catch(Exception e){System.out.println("getDataProjects failure");}


        return list;
    }

    public static void AddProject(String title, String projectDescription, String date, String type, Client client, String manager_name, String cost) throws EmptyInputException, InvalidDateException, InvalidCostException {
        //CheckData();
        Data_Validation.checkTitle(title);
        Data_Validation.checkDescription(projectDescription);
        Data_Validation.checkDate(date);
        Data_Validation.checkCost(cost);
        Connection con = MySQL_Connector.ConnectDB();

        String sql = "INSERT INTO Projects (title,projectDescription,date,type,client_name,Manager_name,cost)values(?,?,?,?,?,?,?)";
        if (client != null && manager_name != null) {
            try {
                PreparedStatement pst = Objects.requireNonNull(con).prepareStatement(sql);
                pst.setString(1, title);
                pst.setString(2, projectDescription);
                pst.setString(3, date);
                pst.setString(4, type);
                pst.setString(5, String.valueOf(client.getId()));
                pst.setString(6, manager_name);
                pst.setString(7, cost);
                pst.execute();

                Email.send_project_creation_invoice(client.getEmail(),title,projectDescription,type,date,cost);

                Popup_Window.confirmation("Project Added Successfully","Add Project");

            } catch (Exception e) {
                Popup_Window.error("Please fill all fields with appropriate data");
            }

        } else if (manager_name == null && client != null) {
            Popup_Window.error("Manager doesn't exist");
        } else if (manager_name != null) {
            Popup_Window.error("Client doesn't exist");
        } else {
            Popup_Window.error("Both Manager and Client don't exist");
        }


    }

    public void deleteProject() {
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "DELETE FROM Projects WHERE id=" + this.getProjectId() + ";";

        try {
            PreparedStatement pst = Objects.requireNonNull(con).prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println("failed to delete");
        }

    }

    public void UpdateProject(String title, String projectDescription, String date, String type, Client client, String manager_name, String cost) throws EmptyInputException, InvalidDateException, InvalidCostException {

        Data_Validation.checkTitle(title);
        Data_Validation.checkDescription(projectDescription);
        Data_Validation.checkDate(date);
        Data_Validation.checkCost(cost);

        Connection con = MySQL_Connector.ConnectDB();

        String sql =
                "UPDATE Projects set title=?,projectDescription=?,date =?,type=?,client_name=?,Manager_name=?,cost=?  WHERE id = ?; ";

        if (client != null && manager_name != null) {
            try {
                PreparedStatement pst;
                pst = Objects.requireNonNull(con).prepareStatement(sql);
                pst.setString(1, title);
                pst.setString(2, projectDescription);
                pst.setString(3, date);
                pst.setString(4, type);
                pst.setString(5, String.valueOf(client.getId()));
                pst.setString(6, manager_name);
                pst.setString(7, cost);
                pst.setString(8, String.valueOf(this.getProjectId()));
                pst.execute();
                Email.send_project_modification_invoice(client.getEmail(),title,projectDescription,type,date,cost);
                Popup_Window.confirmation("Project Updated Successfully","Update Project");

            } catch (Exception e) {
                Popup_Window.error("Please fill all fields with appropriate data");
            }

        } else if (client == null && manager_name != null) {
            Popup_Window.error("Manager doesn't exist");
        } else if (client != null) {
            Popup_Window.error("Client doesn't exist");
        } else {
            Popup_Window.error("Both Manager and Client don't exist");
        }
    }
}
