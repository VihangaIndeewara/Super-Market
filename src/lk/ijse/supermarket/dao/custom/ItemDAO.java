package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;

import lk.ijse.supermarket.entity.Item;
import javafx.collections.ObservableList;


import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,String> {
     ArrayList<Item> getItemData(String code) throws SQLException, ClassNotFoundException;

     ObservableList<String> getItemCodes() throws SQLException, ClassNotFoundException;

     void updateQtyOnHand(int qty,String code) throws SQLException, ClassNotFoundException;

     ArrayList<Item> getItemInfo(String code) throws SQLException, ClassNotFoundException;

     String getMostLeastInfo(String code) throws SQLException, ClassNotFoundException;
}
