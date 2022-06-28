package lk.ijse.supermarket.bo.Custom;

import lk.ijse.supermarket.bo.SuperBO;
import javafx.collections.ObservableList;
import lk.ijse.supermarket.dto.ItemDTO;

import java.sql.SQLException;

public interface ItemBO extends SuperBO {
     String getNewCode() throws SQLException, ClassNotFoundException;

     ObservableList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

     boolean saveItem(ItemDTO i) throws SQLException, ClassNotFoundException;

     boolean updateItem(ItemDTO i) throws SQLException, ClassNotFoundException;

     boolean deleteItem(String code) throws SQLException, ClassNotFoundException ;
}
