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
    private final String Title;
    private final String Day;
    private final String Time;
    private final String Department;
    private final int id;

    private static final ObservableList<String> departments = Departments.getDepartments();

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

    public static ObservableList<String> getDepartments() { return departments; }

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

    public static void add_Meeting(String Title, String Day,String Time,String Department) throws EmptyInputException, InvalidDateException, InvalidTimeException {
        Data_Validation.checkTitle(Title);
        Data_Validation.checkDate(Day);
        Data_Validation.checkTime(Time);
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "insert into meetings (Title, Day, Time, Department) values (?, ?, ? , ?)";

        try {
            PreparedStatement pst = Objects.requireNonNull(con).prepareStatement(sql);
            pst.setString(1, Title);
            pst.setString(2, Day);
            pst.setString(3, Time);
            pst.setString(4, Department);
            //pst.setString(5, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Adding Meeting Success");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public static void edit_Meeting(String Title, String Day,String Time,String Department, String id) throws EmptyInputException, InvalidDateException, InvalidTimeException {
        Data_Validation.checkTitle(Title);
        Data_Validation.checkDate(Day);
        Data_Validation.checkTime(Time);
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "update meetings set Title = ?, Day = ?, Time = ?, Department = ?, id = ? WHERE id = ? ";

        try{
            PreparedStatement pst = Objects.requireNonNull(con).prepareStatement(sql);
            pst.setString(1, Title);
            pst.setString(2, Day);
            pst.setString(3, Time);
            pst.setString(4, Department);
            pst.setString(5, id);
            pst.setString(6, id);
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


}
