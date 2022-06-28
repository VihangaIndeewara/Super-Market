package lk.ijse.supermarket.bo.Custom.impl;

import lk.ijse.supermarket.bo.Custom.ItemBO;
import lk.ijse.supermarket.dao.DAOFactory;
import lk.ijse.supermarket.dao.custom.ItemDAO;
import lk.ijse.supermarket.entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.supermarket.dto.ItemDTO;

import java.sql.SQLException;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    public String getNewCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }

    public ObservableList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {

        ObservableList<Item> all = itemDAO.getAll();
        ObservableList<ItemDTO> allItems= FXCollections.observableArrayList();

        for (Item i:all) {
            allItems.add(new ItemDTO(i.getItemCode(),i.getDescription(),i.getPackSize(),i.getUnitPrice(),i.getQtyOnHand(),i.getUnitDiscount()));
        }
        return allItems;
    }

    public boolean saveItem(ItemDTO i) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(i.getItemCode(),i.getDescription(),i.getSize(),i.getUnitPrice(),i.getQtyOnHand(),i.getUnitDiscount()));
    }

    public boolean updateItem(ItemDTO i) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(i.getItemCode(),i.getDescription(),i.getSize(),i.getUnitPrice(),i.getQtyOnHand(),i.getUnitDiscount()));
    }

    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }


}
