package lk.ijse.supermarket.bo.Custom;

import lk.ijse.supermarket.bo.SuperBO;
import javafx.collections.ObservableList;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageOrderBO extends SuperBO {
     ArrayList<ItemDTO> getItemDetails(String code) throws SQLException, ClassNotFoundException;

     ObservableList<String> getItemCodes() throws SQLException, ClassNotFoundException;

     ArrayList<ItemDTO> getItemInformation(String code) throws SQLException, ClassNotFoundException;

     ArrayList<OrderDetailDTO>  getOrderDetails(String id) throws SQLException, ClassNotFoundException;

     ArrayList<String> getProcessingOrders() throws SQLException, ClassNotFoundException;

     boolean updateOrderDetails(OrderDetailDTO d) throws SQLException, ClassNotFoundException;

     boolean updateOrderStatus(String status,String id) throws SQLException, ClassNotFoundException ;

     boolean deleteOrder(String code) throws SQLException, ClassNotFoundException;

}
