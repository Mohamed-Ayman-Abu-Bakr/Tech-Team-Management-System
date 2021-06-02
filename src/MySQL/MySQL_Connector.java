package MySQL;

import javax.swing.*;
import java.sql.*;

public class MySQL_Connector {

    private static final String db_url = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql4409579";
    private static final String db_username = "sql4409579";
    private static final String db_password = "zhc6fDgqd6";

    public static Connection ConnectDB(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =  DriverManager.getConnection(db_url, db_username, db_password);
            //JOptionPane.showMessageDialog(null, "Connection Established");
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }










}
