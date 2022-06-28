package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;

import lk.ijse.supermarket.entity.Customer;
import javafx.collections.ObservableList;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
     ArrayList<Customer> getCustomerData(String id) throws SQLException, ClassNotFoundException;

     ObservableList<String> getCustomerIds() throws SQLException, ClassNotFoundException;
}
