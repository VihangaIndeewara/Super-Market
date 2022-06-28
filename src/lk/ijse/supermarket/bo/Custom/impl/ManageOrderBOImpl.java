package lk.ijse.supermarket.bo.Custom.impl;

import lk.ijse.supermarket.bo.Custom.ManageOrderBO;
import lk.ijse.supermarket.dao.DAOFactory;
import lk.ijse.supermarket.dao.custom.ItemDAO;
import lk.ijse.supermarket.dao.custom.OrderDAO;
import lk.ijse.supermarket.dao.custom.OrderDetailDAO;
import lk.ijse.supermarket.entity.Item;
import lk.ijse.supermarket.entity.OrderDetail;
import javafx.collections.ObservableList;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageOrderBOImpl implements ManageOrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    public ArrayList<ItemDTO> getItemDetails(String code) throws SQLException, ClassNotFoundException {
        ArrayList<Item> itemData = itemDAO.getItemData(code);

        ArrayList<ItemDTO> allItems=new ArrayList<>();
        for (Item i:itemData) {
            allItems.add(new ItemDTO(i.getDescription(),i.getPackSize(),i.getQtyOnHand(),i.getUnitPrice(),i.getUnitDiscount()));
        }
        return allItems;

    }

    public ObservableList<String> getItemCodes() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemCodes();
    }

    public ArrayList<ItemDTO> getItemInformation(String code) throws SQLException, ClassNotFoundException {
        ArrayList<Item> itemInfo = itemDAO.getItemInfo(code);

        ArrayList<ItemDTO> allItems=new ArrayList<>();
        for (Item i:itemInfo) {
            allItems.add(new ItemDTO(i.getDescription(),i.getPackSize()));
        }
        return allItems;
    }

    public ArrayList<OrderDetailDTO>  getOrderDetails(String id) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> details = orderDetailDAO.getDetails(id);

        ArrayList<OrderDetailDTO> all=new ArrayList<>();
        for (OrderDetail o:details) {
            all.add(new OrderDetailDTO(o.getItemCode(),o.getOrderQty(),o.getUnitPrice(),o.getAmount(),o.getDiscount(),o.getTotal()));
        }
        return all;
    }

    public ArrayList<String> getProcessingOrders() throws SQLException, ClassNotFoundException {
        return orderDAO.getProcessOrders();
    }

    public boolean updateOrderDetails(OrderDetailDTO d) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.updateOrderDetails(new OrderDetail(d.getOrderId(),d.getItemCode(),d.getQty(),d.getUnitPrice(),d.getAmount(),d.getDiscount(),d.getTotal()));
    }

    public boolean updateOrderStatus(String status,String id) throws SQLException, ClassNotFoundException {
        return orderDAO.updateOrderStatus(status,id);

    }

    public boolean deleteOrder(String code) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(code);
    }


}
