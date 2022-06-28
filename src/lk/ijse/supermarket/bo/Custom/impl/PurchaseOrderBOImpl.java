package lk.ijse.supermarket.bo.Custom.impl;

import lk.ijse.supermarket.bo.Custom.PurchaseOrderBO;
import lk.ijse.supermarket.dao.DAOFactory;
import lk.ijse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.supermarket.dao.custom.ItemDAO;
import lk.ijse.supermarket.dao.custom.OrderDAO;
import lk.ijse.supermarket.dao.custom.OrderDetailDAO;
import lk.ijse.supermarket.entity.Customer;
import lk.ijse.supermarket.entity.Item;
import lk.ijse.supermarket.entity.OrderDetail;
import lk.ijse.supermarket.entity.Orders;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.dto.OrderDetailDTO;
import lk.ijse.supermarket.view.TM.CartTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    public void setPurchaseOrder(String oid, String cusId, ObservableList<CartTM> obList) throws SQLException, ClassNotFoundException {
            boolean save = orderDAO.save(new Orders(oid, LocalDate.now(), "process", cusId));
            if (save) {
                setOrderDetails(oid,obList);
            }
    }


    public void setOrderDetails(String oid,ObservableList<CartTM> obList){
        ArrayList<OrderDetailDTO> details=new ArrayList<>();

        for (CartTM t:obList) {
            details.add(new OrderDetailDTO(
                    oid,
                    t.getItemCode(),
                    t.getQty(),
                    t.getUnitPrice(),
                    t.getAmount(),
                    t.getDiscount(),
                    t.getTotal()
            ));
        }
        for (OrderDetailDTO d : details) {


            try {
                boolean save = orderDetailDAO.save(new OrderDetail(d.getOrderId(), d.getItemCode(), d.getQty(), d.getUnitPrice(), d.getAmount(), d.getDiscount(), d.getTotal()));


                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Order Saved").show();
                    updateQtyOnHand(d.getQty(),d.getItemCode());
                    generateOrderId();
                }

            }catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public String generateOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    public ArrayList<ItemDTO> getItemData(String code) throws SQLException, ClassNotFoundException {
        ArrayList<Item> itemData = itemDAO.getItemData(code);

        ArrayList<ItemDTO> all=new ArrayList<>();
        for (Item i:itemData) {
            all.add(new ItemDTO(i.getDescription(),i.getPackSize(),i.getQtyOnHand(),i.getUnitPrice(),i.getUnitDiscount()));
        }
        return all;
    }

    public ObservableList<String> getItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemCodes();
    }

    public ArrayList<CustomerDTO> getCustomerData(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerData = customerDAO.getCustomerData(id);

        ArrayList<CustomerDTO> all =new ArrayList<>();
        for (Customer c : customerData) {
            all.add(new CustomerDTO(c.getCusName(),c.getCusTitle()));
        }
        return all;
    }

    public ObservableList<String> getCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerIds();
    }

    public void updateQtyOnHand(int qty ,String code) throws SQLException, ClassNotFoundException {
        itemDAO.updateQtyOnHand(qty,code);
    }

}
