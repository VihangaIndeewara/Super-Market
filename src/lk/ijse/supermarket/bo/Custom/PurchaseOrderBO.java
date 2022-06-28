package lk.ijse.supermarket.bo.Custom;

import lk.ijse.supermarket.bo.SuperBO;
import javafx.collections.ObservableList;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.view.TM.CartTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {
    public void setPurchaseOrder(String oid, String cusId, ObservableList<CartTM> obList) throws SQLException, ClassNotFoundException ;

    public void setOrderDetails(String oid,ObservableList<CartTM> obList);

    public String generateOrderId() throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> getItemData(String code) throws SQLException, ClassNotFoundException;

    public ObservableList<String> getItemCode() throws SQLException, ClassNotFoundException;

    public ArrayList<CustomerDTO> getCustomerData(String id) throws SQLException, ClassNotFoundException;

    public ObservableList<String> getCustomerId() throws SQLException, ClassNotFoundException;

    public void updateQtyOnHand(int qty ,String code) throws SQLException, ClassNotFoundException;
}
