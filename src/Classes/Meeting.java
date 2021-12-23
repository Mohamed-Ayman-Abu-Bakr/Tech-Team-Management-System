package Classes;

import Exceptions.EmptyInputException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidTimeException;
import MySQL.MySQL_Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;


public class Meeting {
    private  String Title;
    private  String Day;
    private  String Time;
    private  String Department;
    private  int id;



    public Meeting(String title, String day, String time, String dep) {
        this.Title=title;
        this.Day=day;
        this.Time=time;
        this.Department=dep;
    }

    public int getId() {
        return id;
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



    public Meeting(String title, String day, String time, String type, int i) {
        Title = title;
        Day = day;
        Time = time;
        Department = type;
        id = i;
    }


    public static ObservableList <Meeting> getDataMeetings(){
        Connection con = MySQL_Connector.ConnectDB();
        ObservableList <Meeting> list  = FXCollections.observableArrayList();

        try{
            PreparedStatement ps = Objects.requireNonNull(con).prepareStatement("select * from meetings");
            ResultSet rs = ps.executeQuery();

            while(rs.next())  {
                list.add(new Meeting(rs.getString("Title")
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

    public void add_Meeting() {
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "insert into meetings (Title, Day, Time, Department) values (?, ?, ? , ?)";

        try {
            PreparedStatement pst = Objects.requireNonNull(con).prepareStatement(sql);
            pst.setString(1, this.Title);
            pst.setString(2, this.Day);
            pst.setString(3, this.Time);
            pst.setString(4, this.Department);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Adding Meeting Success");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void editMeeting(){

        Connection con = MySQL_Connector.ConnectDB();
        String sql = "update meetings set Title = ?, Day = ?, Time = ?, Department = ? WHERE id = ? ";

        try{
            PreparedStatement pst = Objects.requireNonNull(con).prepareStatement(sql);
            pst.setString(1, this.Title);
            pst.setString(2, this.Day);
            pst.setString(3, this.Time);
            pst.setString(4, this.Department);
            pst.setString(5, String.valueOf(this.id));
            pst.execute();
            Popup_Window.confirmation("Meeting Update Successfully", "Meeting Update");
        }catch (Exception e){
            Popup_Window.error("Cannot Update Meeting");

        }
    }
    public static void delete_Meeting(String id){
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "delete from meetings where id = ?";

        try {
            PreparedStatement pst = Objects.requireNonNull(con).prepareStatement(sql);
            pst.setString(1, id);
            pst.execute();
            Popup_Window.confirmation("Meeting Deleted Successfully","Meeting Delete");
        }
        catch (Exception e){
            Popup_Window.error("Cannot Delete Meeting");
        }
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

    public void setId(int id) {
        this.id = id;
    }
}
