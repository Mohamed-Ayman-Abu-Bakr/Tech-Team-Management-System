package Classes;

import Email_API.Email;
import Exceptions.*;
import MySQL.MySQL_Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Employee {
    private int id,completed_tasks;
    private String name , username , password, email , birthdate, phone;
    private String  position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString(){return name;}
    enum Positions{
        Testing("Testing"),
        Frontend_Web_Development("Frontend web development"),
        Backend_Web_development ("Backend web development"),
        Frontend_Mobile_Development ("Frontend mobile development"),
        Backend_Mobile_development ("Backend mobile development"),
        Data_Analysis ("Data Analysis"),
        Management ("Management"),
        Machine_Learning ("Machine Learning"),
        Database_Administration ("Database Administration"),
        UX_design ("UX design");

        private String pos;
        Positions(String pos){
            this.pos=pos;
        }

        public static String toString(Positions position) {
            return position.pos;
        }
    }

    public Employee(int id, String name, String username, String password,
                    String position, String email, String birthdate, String phone,int completed_tasks) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.position= position;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.completed_tasks = completed_tasks;
    }

    public static ObservableList<String> getPositions(){
        Positions[] poss= Positions.values();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(String.valueOf(poss));
        return list;

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

    public String getEmail() {
        return email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public int getCompleted_tasks() {return completed_tasks;}

    public static String generate_username(int n){
        return RandomString.getAlphaNumericString(n);
    }

    public static String generate_password(int n){
        return RandomString.getAlphaNumericString(n);
    }

    public static Employee login(String email, String password){

        ObservableList<Employee> employees= getDataEmployees();
        boolean foundem=false, foundpass=false;
        for (Employee e:employees){
            foundem=false; foundpass=false;
            String em= e.getEmail();
            if (em.equals(email)) foundem=true;
            String uname= e.getUsername();
            if (uname.equals(email)) foundem=true;
            String pass= e.getPassword();
            if (pass.equals(password)) foundpass=true;
            if (foundem && foundpass){
                return e;
            }
        }
        return null;
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
            pst = Objects.requireNonNull(con).prepareStatement(sql);
            pst.setString(1, password);
            pst.setString(2, String.valueOf(this.id));
            pst.execute();
            Email.send_password_update(this.email,password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public void editEmployee_manager(String name, String email, String phone, String birthdate, String position) throws InvalidEmailException, InvalidNumberException, InvalidDateException, EmptyInputException {
        Data_Validation.checkIfNotEmpty(name);
        Data_Validation.checkEmail(email);
        Data_Validation.checkNum(phone);
        Data_Validation.checkDate(birthdate);
        Connection con;
        PreparedStatement pst;
        con = MySQL_Connector.ConnectDB();
        String sql = "UPDATE employees SET name = ?, email = ?, phone = ?, birthdate = ?, position = ? WHERE (id = ?)";
        try {
            pst = Objects.requireNonNull(con).prepareStatement(sql);
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

    public void editEmployee_employee(String name, String username, String password,String email, String phone)
            throws InvalidEmailException, InvalidNumberException, InvalidDateException,
            EmptyInputException, InvalidUsernameException, InvalidPasswordException {
        Data_Validation.checkIfNotEmpty(name);
        Data_Validation.checkEmail(email);
        Data_Validation.checkNum(phone);
        Data_Validation.checkUsername(username);
        Data_Validation.checkPassword(password);
        Connection con;
        PreparedStatement pst;
        con = MySQL_Connector.ConnectDB();
        String sql = "UPDATE employees SET name = ?, username = ?, password = ?, email = ?, phone = ? WHERE (id = ?)";
        try {
            pst = Objects.requireNonNull(con).prepareStatement(sql);
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
        String sql = "insert into employees (name,username,password,position,email,birthdate,phone)Values(?,?,?,?,?,?,?)";
        try {
            pst = Objects.requireNonNull(con).prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,username);
            pst.setString(3,password);
            pst.setString(4,position);
            pst.setString(5,email);
            pst.setString(6,birthdate);
            pst.setString(7,phone);


            pst.execute();
            Email.send_username_and_password(email,username,password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static ObservableList<Employee> getDataEmployees(){
        Connection con = MySQL_Connector.ConnectDB();
        ObservableList<Employee> list = FXCollections.observableArrayList();

        try{
            PreparedStatement ps = Objects.requireNonNull(con).prepareStatement("SELECT * FROM employees");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Employee(Integer.parseInt(rs.getString("id"))
                        ,rs.getString("name")
                        ,rs.getString("username")
                        ,rs.getString("password")
                        ,rs.getString("position")
                        ,rs.getString("email")
                        ,rs.getString("birthdate")
                        ,rs.getString("phone")
                        ,Integer.parseInt(rs.getString("completed_tasks"))));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }
    public void task_verified(){
        this.completed_tasks++;
        Connection con;
        PreparedStatement pst;
        con = MySQL_Connector.ConnectDB();
        String sql = "UPDATE employees SET name = ?, username = ?, password = ?, email = ?, phone = ?, completed_tasks = ? WHERE (id = ?)";
        try {
            pst = Objects.requireNonNull(con).prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, email);
            pst.setString(5, phone);
            pst.setString(6, String.valueOf(completed_tasks));
            pst.setString(7, String.valueOf(this.getId()));
            pst.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
