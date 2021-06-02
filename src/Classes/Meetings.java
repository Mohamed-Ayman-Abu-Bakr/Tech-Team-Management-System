package Classes;

import MySQL.MySQL_Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.lang.String.valueOf;

public class Meetings {
    private String Title;
    private String Day;
    private String Time;
    private String Department;
    private int id;

    private static ObservableList<String> departments = Departments.getDepartments();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setDay(String day) {
        Day = day;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getTitle() {
        return Title;
    }

    public String getDay() {
        return Day;
    }

    public String getTime() {
        return Time;
    }

    public String getDepartment() {
        return Department;
    }

    public static ObservableList<String> getDepartments() { return departments; }

    public Meetings(String title, String day, String time, String type, int i) {
        Title = title;
        Day = day;
        Time = time;
        Department = type;
        id = i;
    }

    public static ObservableList <Meetings> getDataMeetings(){
        Connection con = MySQL_Connector.ConnectDB();
        ObservableList <Meetings> list  = FXCollections.observableArrayList();

        try{
            PreparedStatement ps = con.prepareStatement("select * from meetings");
            ResultSet rs = ps.executeQuery();

            while(rs.next())  {
                list.add(new Meetings(rs.getString("Title")
                        ,(rs.getString("Day"))
                        ,(rs.getString("Time"))
                        ,rs.getString("Department")
                        ,Integer.parseInt(rs.getString("id"))));
            }

        }catch (Exception e){
            System.out.println("getDataMeetings failure");
        }

        return list;
    }

    public static void add_Meeting(String Title, String Day,String Time,String Department, String id){
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "insert into meetings (Title, Day, Time, Department, id) values (?, ?, ? , ?, ?)";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Title);
            pst.setString(2, Day);
            pst.setString(3, Time);
            pst.setString(4, Department);
            pst.setString(5, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Adding Meeting Success");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public static void edit_Meeting(String Title, String Day,String Time,String Department, String id){
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "update meetings set Title = ?, Day = ?, Time = ?, Department = ?, id = ? WHERE id = ? ";

        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Title);
            pst.setString(2, Day);
            pst.setString(3, Time);
            pst.setString(4, Department);
            pst.setString(5, id);
            pst.setString(6, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,  e);

        }
    }
    public static void delete_Meeting(String id){
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "delete from meetings where id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deleted");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);

        }
    }

}
