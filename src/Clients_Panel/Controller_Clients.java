package Clients_Panel;
import Classes.Clients;
import Exceptions.InvalidAddressException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidNumberException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller_Clients implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField number;

    @FXML
    private TextField address;

    @FXML
    private TableView<Clients> table_clients;

    @FXML
    private TableColumn<Clients, Integer> table_id;

    @FXML
    private TableColumn<Clients, String> table_name;

    @FXML
    private TableColumn<Clients, String> table_email;

    @FXML
    private TableColumn<Clients, String> table_num;

    @FXML
    private TableColumn<Clients, String> table_add;

    ObservableList<Clients> listP;
    int index = -1;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        System.out.println("controller initialized");
        table_id.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("id"));
        table_name.setCellValueFactory(new PropertyValueFactory<Clients, String>("Name"));
        table_email.setCellValueFactory(new PropertyValueFactory<Clients, String>("Email"));
        table_num.setCellValueFactory(new PropertyValueFactory<Clients, String>("Num"));
        table_add.setCellValueFactory(new PropertyValueFactory<Clients, String>("Address"));
        listP = Clients.getClients();
        table_clients.setItems(listP);
    }


    public void addClient (){
        String Cname = name.getText();
        String Cemail = email.getText();
        String Cnum = number.getText();
        String Cadd = address.getText();

        try {
            Clients.add_Client(Cname,Cadd,Cnum,Cemail);
        } catch (InvalidNameException e) {
        } catch (InvalidAddressException e) {
        } catch (InvalidNumberException e) {
        } catch (InvalidEmailException e) {
        }
        UpdateTable();
    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = table_clients.getSelectionModel().getSelectedIndex();
        if(index <= -1) {
            return;
        }
        id.setText(table_id.getCellData(index).toString());
        name.setText(table_name.getCellData(index).toString());
        email.setText(table_email.getCellData(index).toString());
        number.setText(table_num.getCellData(index).toString());
        address.setText(table_add.getCellData(index).toString());
    }

    public void edit(){
        String v1 = id.getText();
        String v2 = name.getText();
        String v3 = email.getText();
        String v4 = number.getText();
        String v5 = address.getText();
        try {
            Clients.edit_Client(v1,v2,v3,v4,v5);
        } catch (InvalidNameException e) {
        } catch (InvalidAddressException e) {
        } catch (InvalidNumberException e) {
        } catch (InvalidEmailException e) {
        }
        UpdateTable();
    }

    public void UpdateTable(){
        table_id.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("id"));
        table_name.setCellValueFactory(new PropertyValueFactory<Clients, String>("Name"));
        table_email.setCellValueFactory(new PropertyValueFactory<Clients, String>("Email"));
        table_num.setCellValueFactory(new PropertyValueFactory<Clients, String>("Num"));
        table_add.setCellValueFactory(new PropertyValueFactory<Clients, String>("Address"));
        listP = Clients.getClients();
        table_clients.setItems(listP);
    }

    public void delete(){
        Clients.delete_Client(id.getText());
        UpdateTable();
    }


}
