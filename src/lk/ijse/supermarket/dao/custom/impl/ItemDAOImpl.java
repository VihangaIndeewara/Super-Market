package lk.ijse.supermarket.dao.custom.impl;

import lk.ijse.supermarket.dao.SQLUtil;
import lk.ijse.supermarket.dao.custom.ItemDAO;
import lk.ijse.supermarket.entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT itemCode FROM Item ORDER BY itemCode DESC LIMIT 1");

        String newId ="ITM001";

        if (rst.next()) {
            String code = rst.getString(1);

            if (code!=null) {
                int num = Integer.valueOf(code.substring(3));
                num++;

                if (num <= 9) {
                    newId = "ITM00" + num;
                } else if (num > 9 && num < 100) {
                    newId = "ITM0" + num;
                } else if (num >= 100 && num < 1000) {
                    newId = "ITM" + num;
                }
            }
        }
        return newId;
    }

    public ObservableList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item");

        ObservableList<Item> obList = FXCollections.observableArrayList();

        while (rst.next()){
            obList.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            ));
        }
        return obList;
    }

    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("INSERT INTO item VALUES (?,?,?,?,?,?)",entity.getItemCode(),entity.getDescription(),entity.getPackSize(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getUnitDiscount());
    }

    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Item SET description=?,packSize=?,unitPrice=?,qtyOnHand=?,unitDiscount=? WHERE itemCode=?",entity.getDescription(),entity.getPackSize(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getUnitDiscount(),entity.getItemCode());
    }

    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Item WHERE itemCode=?",code);
    }

    public ArrayList<Item> getItemData(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT description,packSize,qtyOnHand,unitPrice,unitDiscount FROM Item WHERE itemCode= ?", code);

        ArrayList<Item> list =new ArrayList<>();
        while (rst.next()) {
            list.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5)
            ) );
        }
            return list;
    }

    public ObservableList<String> getItemCodes() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT itemCode FROM Item ");

        ObservableList<String> codeList =FXCollections.observableArrayList();
        while (rst.next()) {
            codeList.add(
                    rst.getString(1)
             );
        }
        return codeList;

    }

    public void updateQtyOnHand(int qty,String code) throws SQLException, ClassNotFoundException {
        SQLUtil.executeUpdate("UPDATE Item SET qtyOnHand=qtyOnHand-? WHERE itemCode=?",qty,code);
    }

    public ArrayList<Item> getItemInfo(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT description,packSize FROM Item WHERE itemCode=?", code);
        ArrayList<Item> list =new ArrayList();

        while (rst.next()) {
            list.add(new Item(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
        return list;
    }

    public String getMostLeastInfo(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT description,packSize FROM Item WHERE itemCode=?", code);
        String info="";

        while (rst.next()) {
            info =rst.getString(1)+ " "+rst.getString(2);
        }
        return info;
    }

}
