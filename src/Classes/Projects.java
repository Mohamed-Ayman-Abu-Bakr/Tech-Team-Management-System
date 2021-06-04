package Classes;

import Email_API.Email;
import MySQL.MySQL_Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Projects {

    private int projectId;
    private String projectTitle;
    private String dateOfDelivery;
    private String projectDescriPtion;
    private int client_id;
    private String Type;
    private int Manager;
    private float cost;
    private String PaymentMethod;


    //Department department;
    //Manager manager;
    //Client client;

    public Projects(int projectId,
                    String projectTitle,
                    String projectDescribtion,
                    String dateOfDelivery,
                    String Type,
                    int client_id,
                    int Manager,


                    float cost, String paymentMethod) {
        this.projectId = projectId;
        this.client_id = client_id;
        this.projectTitle = projectTitle;
        this.dateOfDelivery = dateOfDelivery;
        this.projectDescriPtion = projectDescribtion;
        this.Type = Type;
        this.Manager = Manager;
        this.cost = cost;
        PaymentMethod = paymentMethod;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public void setProjectDescriPtion(String projectDescriPtion) {
        this.projectDescriPtion = projectDescriPtion;
    }

    public void setClient_name(int client_id) {
        this.client_id = client_id;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setManager(int Manager) {
        this.Manager = Manager;
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

    public String getProjectDescriPtion() {
        return projectDescriPtion;
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

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    private static ObservableList<String> projectTypes = FXCollections.observableArrayList("Game", "Mobile App", "Desktop App", "Web App", "Embedded Sys. App", "Data analysis");

    public static ObservableList<String> getProjectTypes() {
        return projectTypes;
    }

    public static ObservableList<Projects> getDataProjects(){
        Connection con = MySQL_Connector.ConnectDB();
        ObservableList<Projects> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM sql4409579.Projects");
            ResultSet rs = ps.executeQuery();
            System.out.println("getDataProjects success");

            while(rs.next()){
                list.add(new Projects(Integer.parseInt(

                        rs.getString("id"))
                                ,rs.getString("title")
                                ,rs.getString("projectDescription")
                                ,rs.getString("date")
                                ,rs.getString("type")
                                ,rs.getInt("client_name")
                                ,rs.getInt("Manager_name")
                                ,rs.getFloat("cost")
                                ,rs.getString("Payment_method")
                        )

                );
            }
        }catch(Exception e){System.out.println("getDataProjects failure");}


        return list;
    }

    public static void AddProject(String title, String projectDescription, String date, String type, Clients client, String manager_name, String cost) {
        //CheckData();
        Connection con = MySQL_Connector.ConnectDB();

        String sql = "INSERT INTO sql4409579.Projects (title,projectDescription,date,type,client_name,Manager_name,cost)values(?,?,?,?,?,?,?)";
        if (client != null && manager_name != null) {
            try {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, title);
                pst.setString(2, projectDescription);
                pst.setString(3, date);
                pst.setString(4, type);
                pst.setString(5, String.valueOf(client.getId()));
                pst.setString(6, manager_name);
                pst.setString(7, cost);
                pst.execute();

                Email.send_project_creation_invoice(client.getEmail(),title,projectDescription,type,date,cost);

                JOptionPane.showMessageDialog(null, "Project add success");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "please fill all fields with appropriate data");
            }

        } else if (manager_name == null && client != null) {
            JOptionPane.showMessageDialog(null, "Manager doesn't exist");
        } else if (client == null && manager_name != null) {
            JOptionPane.showMessageDialog(null, "Client doesn't exist");
        } else {
            JOptionPane.showMessageDialog(null, "Both Manager and Client don't exist");
        }


    }

    public void deleteProject() {
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "DELETE FROM sql4409579.Projects WHERE id=" + this.getProjectId() + ";";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println("failed to delete");
        }

    }

    public void UpdateProject(String title, String projectDescription,String date, String type, Clients client, String manager_name, String cost) {

        Connection con = MySQL_Connector.ConnectDB();

        String sql =
                "UPDATE sql4409579.Projects set title=?,projectDescription=?,date =?,type=?,client_name=?,Manager_name=?,cost=?  WHERE id = ?; ";

        if (client != null && manager_name != null) {
            try {
                PreparedStatement pst = con.prepareStatement(sql);
                pst = con.prepareStatement(sql);
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
                JOptionPane.showMessageDialog(null, "Project updated successfully");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "please fill all fields with appropriate data");
            }

        } else if (client == null && manager_name != null) {
            JOptionPane.showMessageDialog(null, "Manager doesn't exist");
        } else if (client != null && manager_name == null) {
            JOptionPane.showMessageDialog(null, "Client doesn't exist");
        } else {
            JOptionPane.showMessageDialog(null, "Both Manager and Client don't exist");
        }
    }
}
