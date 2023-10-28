package controller;

import javafx.collections.ObservableList;
import model.Customer;

import java.util.Observable;

public interface CustomerService {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    Customer searchCustomer(String id);
    Customer deleteCustomer(String id);
    ObservableList<Customer> getAllCustomer();


}
