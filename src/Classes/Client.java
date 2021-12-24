package Classes;

import Exceptions.*;
import MySQL.MySQL_Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Client {
    private int id;
    private String name, email, address,num;

    public Client(int id, String clientName, String phoneNumber, String email, String address) {
        this.id = id;
        name = clientName;
        this.email = email;
        this.address = address;
        num = phoneNumber;
    }
    public Client(String clientName, String phoneNumber, String email, String address) {
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

    public static void getClient_from_id(int id, Client client){
        Connection con = MySQL_Connector.ConnectDB();
        try{
            PreparedStatement ps = Objects.requireNonNull(con).prepareStatement("SELECT * FROM Clients where id = " + id);
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

    public static ObservableList<Client> getClients() {
        Connection con = MySQL_Connector.ConnectDB();
        ObservableList<Client> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = Objects.requireNonNull(con).prepareStatement("SELECT * FROM Clients");
            ResultSet rs = ps.executeQuery();
            System.out.println("getDataClients success");

            while(rs.next()){
                list.add(new Client(Integer.parseInt(

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

    public  void add_Client() {

        Connection con = MySQL_Connector.ConnectDB();
        String sql = "INSERT INTO Clients VALUES (default, ?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = Objects.requireNonNull(con).prepareStatement(sql);
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.num);
            pstmt.setString(3, this.email);
            pstmt.setString(4, this.address);
            pstmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            //System.out.println("CLIENT UNSUCCESSFULLY ADDED");
        }
    }
    public  void edit_Client() {

        try {
            Connection con = MySQL_Connector.ConnectDB();
            String sql = "Update Clients set Name= '" + this.name + "' ,Email= '" + this.email
                    + "' ,Number= '" + this.num + "' , Address= '" + this.address + "' where id= '" + id + "' ";
            PreparedStatement pstmt = Objects.requireNonNull(con).prepareStatement(sql);
            pstmt.execute();
            Popup_Window.confirmation("Client Updated Successfully","Client Update");
        } catch (Exception e) {
            Popup_Window.error("Cannot Update Client");
        }
    }
    public  void delete_Client(){
        Connection con =  MySQL_Connector.ConnectDB();
        String sql = "delete from Clients where id = ?";
        try {
            PreparedStatement pstmt = Objects.requireNonNull(con).prepareStatement(sql);
            pstmt.setString(1, String.valueOf(this.id));
            pstmt.execute();
            Popup_Window.confirmation("Client Deleted Successfully","Client Delete");
        } catch (Exception e) {
            Popup_Window.error("Cannot Delete Client");
        }
    }
}
