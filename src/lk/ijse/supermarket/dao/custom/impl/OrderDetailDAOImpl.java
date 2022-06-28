package lk.ijse.supermarket.dao.custom.impl;

import lk.ijse.supermarket.dao.SQLUtil;
import lk.ijse.supermarket.dao.custom.OrderDetailDAO;
import lk.ijse.supermarket.entity.OrderDetail;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO orderDetail VALUES (?,?,?,?,?,?,?)",entity.getOrderId(),entity.getItemCode(),entity.getOrderQty(),entity.getUnitPrice(),entity.getAmount(),entity.getDiscount(),entity.getTotal());
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ArrayList<OrderDetail> getDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT itemCode,OrderQty,unitPrice,amount,discount,total FROM orderDetail WHERE orderId=?", id);

        ArrayList<OrderDetail> list =new ArrayList();

        while (rst.next()) {
            list.add(new OrderDetail(
                    rst.getString(1),
                    rst.getInt(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6)
            ));
        }
        return list;
    }

    public boolean updateOrderDetails(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("Update orderDetail SET orderQty=?,amount=?,discount=?,total=? WHERE orderId=?",entity.getOrderQty(),entity.getAmount(),entity.getDiscount(),entity.getTotal(),entity.getOrderId());
    }

    public String leastMovableItem() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT itemCode,SUM( orderQty) FROM  orderDetail GROUP BY (itemCode) ORDER BY orderQty ASC LIMIT 1 ;");

        String leastItemCode="";

        while (rst.next()) {
            leastItemCode = rst.getString(1);
        }
        return leastItemCode;
    }

    public String mostMovableItem() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT  itemCode,SUM( orderQty) FROM  orderDetail GROUP BY (itemCode) ORDER BY orderQty DESC LIMIT 1 ;");
        String mostItemCode="";

        while (rst.next()) {
            mostItemCode = rst.getString(1);
        }
        return mostItemCode;
    }


    public double getSumOfTotal(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT SUM(total) FROM orderDetail WHERE orderId=?", id);
        double tot=0;

        while (rst.next()) {
            tot+= rst.getDouble(1);
        }
        return tot;


    }
}
