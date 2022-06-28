package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Orders,String> {
     ArrayList<String> getProcessOrders() throws SQLException, ClassNotFoundException;

     boolean updateOrderStatus(String status,String id) throws SQLException, ClassNotFoundException;

     ArrayList<String> getDailyIncome(String date) throws SQLException,ClassNotFoundException;

     ArrayList<String> getMonthlyIncome() throws SQLException,ClassNotFoundException ;

     ArrayList<String> getAnnuallyIncome() throws SQLException,ClassNotFoundException;
}
