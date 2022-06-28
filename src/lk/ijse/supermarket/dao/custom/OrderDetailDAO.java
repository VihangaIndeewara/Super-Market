package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;

import lk.ijse.supermarket.entity.OrderDetail;


import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail,String> {

     ArrayList<OrderDetail> getDetails(String id) throws SQLException, ClassNotFoundException ;

     boolean updateOrderDetails(OrderDetail d) throws SQLException, ClassNotFoundException;

     String leastMovableItem() throws SQLException, ClassNotFoundException;

     String mostMovableItem() throws SQLException, ClassNotFoundException;

     double getSumOfTotal(String id) throws SQLException, ClassNotFoundException;
}
