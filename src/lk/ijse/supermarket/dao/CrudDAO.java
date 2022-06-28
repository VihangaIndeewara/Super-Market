package lk.ijse.supermarket.dao;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CrudDAO<T,Id> extends SuperDAO{
    public String generateNewId() throws SQLException, ClassNotFoundException;

    public ObservableList<T> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(T i) throws SQLException, ClassNotFoundException ;

    public boolean update(T dto) throws SQLException, ClassNotFoundException;

    public boolean delete(Id code) throws SQLException, ClassNotFoundException;

}
