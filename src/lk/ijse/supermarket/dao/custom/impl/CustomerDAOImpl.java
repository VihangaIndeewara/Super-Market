package lk.ijse.supermarket.dao.custom.impl;

import lk.ijse.supermarket.dao.SQLUtil;
import lk.ijse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.supermarket.entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("INSERT INTO Customer Values (?,?,?,?,?,?,?)",entity.getCusId(),entity.getCusName(),entity.getCusTitle(),entity.getCusAddress(),entity.getProvince(),entity.getCity(),entity.getPostalCode());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT cusId FROM Customer ORDER BY cusId DESC LIMIT 1 ");
        String newId="C001";

        while (rst.next()) {
            String id = rst.getString(1);

            if(id!=null){
                int num = Integer.valueOf(id.substring(1));
                num++;

                if(num<=9){
                    newId="C00"+num;
                }else if (num>9&&num<100){
                    newId="C0"+num;
                }else if (num>=100){
                    newId="C"+num;
                }
            }
        }
        return newId;
    }

    @Override
    public ObservableList<Customer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public ArrayList<Customer> getCustomerData(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT cusName,cusTitle FROM Customer WHERE cusId=?", id);

        ArrayList<Customer> list =new ArrayList<>();
        while (rst.next()) {
            list.add(new Customer(
                    rst.getString(1),
                    rst.getString(2)
            ) );
        }
        return list;
    }

    public ObservableList<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT cusId FROM CUSTOMER");

        ObservableList<String> codeList = FXCollections.observableArrayList();
        while (rst.next()) {
            codeList.add(
                    rst.getString(1)
            );
        }
        return codeList;

    }


}
