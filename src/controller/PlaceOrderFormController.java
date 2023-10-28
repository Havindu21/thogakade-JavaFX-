package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Customer;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {
    public ComboBox cmbCustomerId;
    public ComboBox cmbItemCode;
    public Label lblDate;
    public TextField txtCustomerSalary;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtPrice;
    public TextField txtDesc;
    public TextField txtQtyOnHand;

    void loadCustomerIDS() {
        ArrayList<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);

    }
    void loadItemCodes() {
        ArrayList<String> itemCodes = new CustomerController().getItemCodes();
        cmbItemCode.getItems().addAll(itemCodes);

    }
    void loadDate(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        lblDate.setText(f.format(date));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCustomerIDS();
        loadItemCodes();
        loadDate();
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) throws Exception {
        String id=cmbCustomerId.getSelectionModel().getSelectedItem().toString();
        txtCustomerName.setText(CustomerController.searchCustomerById(id).getName());
        txtCustomerAddress.setText(CustomerController.searchCustomerById(id).getAddress());
        txtCustomerSalary.setText(CustomerController.searchCustomerById(id).getSalary()+"");
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code=cmbItemCode.getSelectionModel().getSelectedItem().toString();
        txtDesc.setText(CustomerController.searchItemByCode(code).getDescription());
        txtPrice.setText(CustomerController.searchItemByCode(code).getUnitPrice()+"");
        txtQtyOnHand.setText(CustomerController.searchItemByCode(code).getQtyOnHand()+"");
    }
}