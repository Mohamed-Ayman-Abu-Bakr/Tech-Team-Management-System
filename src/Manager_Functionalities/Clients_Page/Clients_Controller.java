package Manager_Functionalities.Clients_Page;
import Classes.Client;
import Classes.Data_Validation;
import Exceptions.*;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class Clients_Controller implements Initializable {

    @FXML
    private Button btn_Update;

    @FXML
    private Button btn_delete;

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
    private TableView<Client> table_clients;

    @FXML
    private TableColumn<Client, Integer> table_id;

    @FXML
    private TableColumn<Client, String> table_name;

    @FXML
    private TableColumn<Client, String> table_email;

    @FXML
    private TableColumn<Client, String> table_num;

    @FXML
    private TableColumn<Client, String> table_add;

    ObservableList<Client> listP;
    int index = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        System.out.println("controller initialized");
        table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        table_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        table_num.setCellValueFactory(new PropertyValueFactory<>("Num"));
        table_add.setCellValueFactory(new PropertyValueFactory<>("Address"));
        listP = Client.getClients();
        table_clients.setItems(listP);
        enableButtons();
    }


    public void addClient (){
        String Cname = name.getText();
        String Cemail = email.getText();
        String Cnum = number.getText();
        String Cadd = address.getText();

        try {
            Data_Validation.checkIfNotEmpty(Cname);
            Data_Validation.checkIfNotEmpty(Cadd);
            Data_Validation.checkNum(Cnum);
            Data_Validation.checkEmail(Cemail);

            Client.add_Client(Cname,Cadd,Cnum,Cemail);
        } catch (InvalidNumberException | InvalidEmailException | EmptyInputException e) {
            System.out.println(e);
        }
        UpdateTable();
        resetValues();
    }

    public void getSelected() {
        index = table_clients.getSelectionModel().getSelectedIndex();
        if(index <= -1) {
            return;
        }
        id.setText(table_id.getCellData(index).toString());
        name.setText(table_name.getCellData(index));
        email.setText(table_email.getCellData(index));
        number.setText(table_num.getCellData(index));
        address.setText(table_add.getCellData(index));
    }

    public void edit(){
        String newId = id.getText();
        String newName = name.getText();
        String newEmail = email.getText();
        String newNumber = number.getText();
        String newAddress = address.getText();
        try {
            Data_Validation.checkIfNotEmpty(newName);
            Data_Validation.checkIfNotEmpty(newAddress);
            Data_Validation.checkNum(newNumber);
            Data_Validation.checkEmail(newEmail);

            Client.edit_Client(newId,newName,newEmail,newNumber,newAddress);
        } catch (InvalidNumberException | InvalidEmailException | EmptyInputException e) {
            System.out.println(e);
        }
        UpdateTable();
    }

    public void UpdateTable(){
        table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        table_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        table_num.setCellValueFactory(new PropertyValueFactory<>("Num"));
        table_add.setCellValueFactory(new PropertyValueFactory<>("Address"));
        listP = Client.getClients();
        table_clients.setItems(listP);
    }

    public void enableButtons(){
        btn_Update.disableProperty().bind(Bindings.isEmpty(table_clients.getSelectionModel().getSelectedItems()));
        btn_delete.disableProperty().bind(Bindings.isEmpty(table_clients.getSelectionModel().getSelectedItems()));
    }
    public void resetValues(){
        id.setText("");
        name.setText("");
        email.setText("");
        number.setText("");
        address.setText("");
    }


    public void delete(){
        Client.delete_Client(id.getText());
        UpdateTable();
        resetValues();
    }


}
