
package Projects_Panel;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class ProjectStatsController implements Initializable {

    @FXML
    private PieChart Chart;
    Connection con;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> list=  FXCollections.observableArrayList();
        Type t= new Type();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection ("jdbc:mysql://sql4.freesqldatabase.com:3306/sql4409579", "sql4409579", "zhc6fDgqd6");
            Statement stmt;  ResultSet rs;
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Projects");
            while (rs.next()){
                String type = rs.getString("type");
                if (type.equals("Game")) t.gameCnt=t.getGameCnt()+1;
                else if (type.equals("Mobile App")) t.mobileAppCnt=t.getMobileAppCnt()+1;
                else if (type.equals("Desktop App")) t.desktopAppCnt=t.getDesktopAppCnt()+1;
                else if (type.equals("Web App")) t.webAppCnt=t.getWebAppCnt()+1;
                else if (type.equals("Embedded Sys. App")) t.EmbeddedSysCnt=t.getEmbeddedSysCnt()+1;
                else if (type.equals("Data analysis")) t.dataAnalysisCnt=t.getDataAnalysisCnt()+1;
            }
            list.add(new PieChart.Data("Game", t.gameCnt));
            list.add(new PieChart.Data("Mobile App", t.mobileAppCnt));
            list.add(new PieChart.Data("Desktop App", t.desktopAppCnt));
            list.add(new PieChart.Data("Web App", t.webAppCnt));
            list.add(new PieChart.Data("Embedded Sys. App", t.EmbeddedSysCnt));
            list.add(new PieChart.Data("Data analysis", t.dataAnalysisCnt));

            Chart.setData(list);
            Chart.setClockwise(true);

        } catch (SQLException ex) {
            Logger.getLogger(ProjectStatsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectStatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
