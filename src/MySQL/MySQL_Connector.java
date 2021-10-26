package MySQL;


import Classes.Popup_Window;
import java.sql.*;


public class MySQL_Connector {

    private static final String db_url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11446943";
    private static final String db_username = "sql11446943";
    private static final String db_password = "LMQlp9BulM";

    public static Connection ConnectDB(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(db_url, db_username, db_password);
        } catch (Exception e) {
            Popup_Window.error("Cannot Connect to Database");
            return null;
        }
    }










}
