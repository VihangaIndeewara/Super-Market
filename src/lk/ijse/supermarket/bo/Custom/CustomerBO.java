package lk.ijse.supermarket.bo.Custom;

import lk.ijse.supermarket.bo.SuperBO;
import lk.ijse.supermarket.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {
     String getNewId() throws SQLException, ClassNotFoundException ;

     boolean saveCustomer(CustomerDTO c) throws SQLException, ClassNotFoundException;
}
