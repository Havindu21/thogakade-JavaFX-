package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController  implements Initializable {
    public TextField txtId;
    public TextField txtSalary;
    public TextField txtName;
    public TextField txtAddress;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;

    private ObservableList<Customer> tblCustomerList;

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        double salary=Double.parseDouble(txtSalary.getText());

        Customer customer=new Customer(id,name,address,salary);

        boolean isAdded=new CustomerController().addCustomer(customer);
        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added").show();
        }
        loadTable();
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Customer customer=new CustomerController().searchCustomer(id);
        if (customer!=null){
            new Alert(Alert.AlertType.INFORMATION,"");
        }
    }
    public void loadTable(){
        ObservableList<Customer> allCustomer = new CustomerController().getAllCustomer();
        tblCustomer.setItems(allCustomer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        loadTable();
    }
}