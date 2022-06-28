package lk.ijse.supermarket.bo.Custom;

import lk.ijse.supermarket.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReportBO extends SuperBO {
     String getLeastMovableItem() throws SQLException, ClassNotFoundException;

     String getMostMovableItem() throws SQLException, ClassNotFoundException;

     String getItemDescription(String code) throws SQLException, ClassNotFoundException;

     ArrayList<String> getDailyIncome(String date) throws SQLException, ClassNotFoundException ;

     ArrayList<String> getMonthlyIncome() throws SQLException, ClassNotFoundException;

     ArrayList<String> getAnnuallyIncome() throws SQLException, ClassNotFoundException;

     double getSumOfTotal(String id) throws SQLException, ClassNotFoundException;
}
