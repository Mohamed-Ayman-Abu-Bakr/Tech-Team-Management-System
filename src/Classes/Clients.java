package Classes;

import MySQL.MySQL_Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Clients {
    private int id;
    private String name, email, address,num;

    public Clients(int id, String clientName, String phoneNumber, String email, String address) {
        this.id = id;
        name = clientName;
        this.email = email;
        this.address = address;
        num = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getNum() {
        return num;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setNum(String num){
        this.num = num;
    }

    public static void getClient_from_id(int id, Clients client){
        Connection con = MySQL_Connector.ConnectDB();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM sql4409579.Clients where id = " + id);
            ResultSet rs = ps.executeQuery();
            System.out.println("getDataClients success");
            while(rs.next()){
                client.setId(Integer.parseInt(rs.getString("id")));
                client.setName(rs.getString("Name"));
                client.setNum(rs.getString("Number"));
                client.setEmail(rs.getString("Email"));
                client.setAddress(rs.getString("Address"));
            }
        } catch (Exception e){System.out.println("get client failure");}
    }

    public static ObservableList<Clients> getClients() {
        Connection con = MySQL_Connector.ConnectDB();
        ObservableList<Clients> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM sql4409579.Clients");
            ResultSet rs = ps.executeQuery();
            System.out.println("getDataClients success");

            while(rs.next()){
                list.add(new Clients(Integer.parseInt(

                        rs.getString("id"))
                                ,rs.getString("Name")
                                ,rs.getString("Number")
                                ,rs.getString("Email")
                                ,rs.getString("Address")

                        )
                );
            }
        }catch(Exception e){System.out.println("getDataClients failure");}

        return list;
    }

    public static void add_Client(String name, String address, String number, String email){
        Connection con = MySQL_Connector.ConnectDB();
        String sql = "INSERT INTO sql4409579.Clients VALUES (default, ?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, number);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            //System.out.println(name);
            //System.out.println("CLIENT SUCCESSFULLY ADDED.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //System.out.println("CLIENT UNSUCCESSFULLY ADDED");
        }
    }
    public static void edit_Client(String id,String newName,String newEmail,String newNumber, String newAddress){
        try {
            Connection con = MySQL_Connector.ConnectDB();
            String sql = "Update sql4409579.Clients set Name= '" + newName + "' ,Email= '" + newEmail
                    + "' ,Number= '" + newNumber + "' , Address= '" + newAddress + "' where id= '" + id + "' ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.execute();
            JOptionPane.showMessageDialog(null,"update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static void delete_Client(String id){
        Connection con =  MySQL_Connector.ConnectDB();
        String sql = "delete from sql4409579.Clients where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.execute();
            JOptionPane.showMessageDialog(null, "delete");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
