package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;
import model.Item;

import java.sql.*;
import java.util.ArrayList;

public class CustomerController implements CustomerService {

    public static Customer searchCustomerById(String id) throws Exception {
        String SQL = "select * from customer where id='"+id+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        if (resultSet.next()){
            return new Customer(id,resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
        }else {
            return null;
        }
        // PreparedStatement pstm = DBConnection.getInstance().getConnection().createStatement("Select * From Customer where id='"+id+"'");
    }
    public static Item searchItemByCode(String code) throws SQLException, ClassNotFoundException {
        String SQL = "select * from item where code='"+code+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        if (resultSet.next()){
            return new Item(code,resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4));
        }else {
            return null;
        }
        // PreparedStatement pstm = DBConnection.getInstance().getConnection().createStatement("Select * From Customer where id='"+id+"'");
    }

    @Override
    public boolean addCustomer(Customer customer) {
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
            pstm.setObject(1, customer.getId());
            pstm.setObject(2, customer.getName());
            pstm.setObject(3, customer.getAddress());
            pstm.setObject(4, customer.getSalary());
            if (pstm.executeUpdate() > 0) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer searchCustomer(String id) {
        return null;
    }

    @Override
    public Customer deleteCustomer(String id) {
        return null;
    }

    @Override
    public ObservableList<Customer> getAllCustomer () {
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4));
                customersList.add(customer);
            }
            return customersList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getCustomerIds(){
        ArrayList<String> ids = new ArrayList<>();
        String SQL = "SELECT * FROM Customer";
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rst = stm.executeQuery();
            while(rst.next()){
                ids.add(rst.getString(1));
            }
            return ids;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<String> getItemCodes(){
        ArrayList<String> codes = new ArrayList<>();
        String SQL = "SELECT * FROM Item";
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rst = stm.executeQuery();
            while(rst.next()){
                codes.add(rst.getString(1));

            }
            return codes;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}