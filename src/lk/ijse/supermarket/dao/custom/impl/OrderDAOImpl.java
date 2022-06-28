package lk.ijse.supermarket.dao.custom.impl;

import lk.ijse.supermarket.dao.SQLUtil;
import lk.ijse.supermarket.dao.custom.OrderDAO;
import lk.ijse.supermarket.entity.Orders;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");

        String newOrderId ="OR001";

        while (rst.next()) {
            String id = rst.getString(1);

            if (id!=null) {
                int num = Integer.parseInt(id.substring(2));
                num++;

                if (num <= 9) {
                    newOrderId = "OR00" + num;
                } else if (num > 9 && num < 100) {
                    newOrderId = "OR0" + num;
                } else if (num >= 100) {
                    newOrderId = "OR" + num;
                }
            }
        }
        return newOrderId;
    }
    @Override
    public ObservableList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("INSERT INTO orders VALUES (?,?,?,?)",entity.getOrderId(),entity.getDate(),entity.getStatus(),entity.getCusId());
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Orders WHERE orderId=?",code);
    }

    public ArrayList<String> getProcessOrders() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT orderId FROM Orders WHERE status='Process'");

        ArrayList<String> idList=new ArrayList<>();

        while (rst.next()) {
            idList.add(rst.getString(1));
        }
            return idList;
        }

    public boolean updateOrderStatus(String status,String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Orders SET status=? WHERE orderId=?",status,id);
    }


    public ArrayList<String> getDailyIncome(String date) throws SQLException,ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT orderId FROM orders WHERE orderDate=?", date);

        ArrayList<String> orderIdList=new ArrayList<>();

            while (rst.next()) {
                orderIdList.add(rst.getString(1));
            }
            return  orderIdList;

    }

    public ArrayList<String> getMonthlyIncome() throws SQLException,ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT orderId FROM orders WHERE orderDate BETWEEN '2022-06-01' and '2022-06-30'");

        ArrayList<String> orderIdList=new ArrayList<>();

        while (rst.next()) {
            orderIdList.add(rst.getString(1));
        }
        return  orderIdList;

    }

    public ArrayList<String> getAnnuallyIncome() throws SQLException,ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT orderId FROM orders WHERE orderDate BETWEEN '2022-06-01' and '2023-05-31'");

        ArrayList<String> orderIdList=new ArrayList<>();

        while (rst.next()) {
            orderIdList.add(rst.getString(1));
        }
        return  orderIdList;

    }

}

