package Classes;

import Email_API.Email;
import MySQL.MySQL_Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employees {
    private int id;
    private String name , username , password , position , email , birthdate, phone;

    public Employees(int id, String name, String username, String password, String position, String email, String birthdate, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.position = position;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
    }



    //private static ObservableList<String> positions = FXCollections.observableArrayList("Manager","Developer","Tester");
    private static ObservableList<String> positions = Departments.getDepartments();


    public static ObservableList<String> getPositions() {
        return positions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public static String generate_username(int n){
        return RandomString.getAlphaNumericString(n);
    }

    public static String generate_password(int n){
        return RandomString.getAlphaNumericString(n);
    }

    public static ResultSet getDataEmployee(){
        ResultSet rs= null;
        try {
            Connection con = MySQL_Connector.ConnectDB();
            PreparedStatement ps = con.prepareStatement("select * from employees");
            rs=ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(MySQL_Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void deleteEmployee(){
        Connection con;
        PreparedStatement pst;
        con = MySQL_Connector.ConnectDB();
        String sql = "delete from employees where id = ?";
        try {
            assert con != null;
            pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(this.id));
            pst.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void resetPassword(){
        String password = generate_password(10);
        Connection con;
        PreparedStatement pst;
        con = MySQL_Connector.ConnectDB();
        String sql = "UPDATE employees SET password = ? WHERE (id = ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, password);
            pst.setString(2, String.valueOf(this.id));
            pst.execute();
            Email.send_password_update(this.email,password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public void editEmployee_manager(String name, String email, String phone, String birthdate, String position){
        Connection con;
        PreparedStatement pst;
        con = MySQL_Connector.ConnectDB();
        String sql = "UPDATE employees SET name = ?, email = ?, phone = ?, birthdate = ?, position = ? WHERE (id = ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, phone);
            pst.setString(4, birthdate);
            pst.setString(5, position);
            pst.setString(6, String.valueOf(this.getId()));
            pst.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void editEmployee_employee(String name, String username, String password, String email, String phone){
        Connection con;
        PreparedStatement pst;
        con = MySQL_Connector.ConnectDB();
        String sql = "UPDATE employees SET name = ?, username = ?, password = ?, email = ?, phone = ? WHERE (id = ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, email);
            pst.setString(5, phone);
            pst.setString(6, String.valueOf(this.getId()));
            pst.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void addEmployee(String name, String email, String phone, String birthdate, String position){
        String username = generate_username(10);
        String password = generate_password(10);
        Connection con;
        PreparedStatement pst;
        con = MySQL_Connector.ConnectDB();
        String sql = "insert into employees (name,username,password,email,phone,birthdate,position)Values(?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,username);
            pst.setString(3,password);
            pst.setString(4,email);
            pst.setString(5,phone);
            pst.setString(6,birthdate);
            pst.setString(7,position);
            pst.execute();
            Email.send_username_and_password(email,username,password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static ObservableList<Employees> getDataEmployees(){
        Connection con = MySQL_Connector.ConnectDB();
        ObservableList<Employees> list = FXCollections.observableArrayList();

        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employees");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Employees(Integer.parseInt(rs.getString("id"))
                        ,rs.getString("name")
                        ,rs.getString("username")
                        ,rs.getString("password")
                        ,rs.getString("position")
                        ,rs.getString("email")
                        ,rs.getString("birthdate")
                        ,rs.getString("phone")));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public void displayEmployee(){
        System.out.println(name);
        System.out.println(email);
    }
}
