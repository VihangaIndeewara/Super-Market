package lk.ijse.supermarket.bo.Custom.impl;

import lk.ijse.supermarket.bo.Custom.ReportBO;
import lk.ijse.supermarket.dao.DAOFactory;
import lk.ijse.supermarket.dao.custom.ItemDAO;
import lk.ijse.supermarket.dao.custom.OrderDAO;
import lk.ijse.supermarket.dao.custom.OrderDetailDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportBOImpl implements ReportBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    public String getLeastMovableItem() throws SQLException, ClassNotFoundException {
        return orderDetailDAO.leastMovableItem();
    }

    public String getMostMovableItem() throws SQLException, ClassNotFoundException {
        return orderDetailDAO.mostMovableItem();
    }

    public String getItemDescription(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.getMostLeastInfo(code);
    }

    public ArrayList<String> getDailyIncome(String date) throws SQLException, ClassNotFoundException {
        return orderDAO.getDailyIncome(date);
    }

    public ArrayList<String> getMonthlyIncome() throws SQLException, ClassNotFoundException {
        return orderDAO.getMonthlyIncome();
    }

    public ArrayList<String> getAnnuallyIncome() throws SQLException, ClassNotFoundException {
        return orderDAO.getAnnuallyIncome();
    }

    public double getSumOfTotal(String id) throws SQLException, ClassNotFoundException {
         return orderDetailDAO.getSumOfTotal(id);
    }
}
