package lk.ijse.supermarket.bo.Custom.impl;

import lk.ijse.supermarket.bo.Custom.CustomerBO;
import lk.ijse.supermarket.dao.DAOFactory;
import lk.ijse.supermarket.dao.SuperDAO;
import lk.ijse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.entity.Customer;

import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public String getNewId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    public boolean saveCustomer(CustomerDTO c) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(c.getCusId(),c.getName(),c.getTitle(),c.getAddress(),c.getCity(),c.getProvince(),c.getPostalCode()));
    }
}
