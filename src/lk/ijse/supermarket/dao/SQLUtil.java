package lk.ijse.supermarket.dao;

import lk.ijse.supermarket.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    private static PreparedStatement getPreparedStatement(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);

        for (int i=0;i<args.length;i++){
            stm.setObject(i+1,args[i]);
        }

        return stm;
    }

    public static boolean executeUpdate(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getPreparedStatement(sql, args);
        return preparedStatement.executeUpdate()>0;
    }

    public static ResultSet executeQuery(String sql, Object...args) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getPreparedStatement(sql, args);
        return preparedStatement.executeQuery();
    }
}
