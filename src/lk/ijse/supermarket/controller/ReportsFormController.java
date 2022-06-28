package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.bo.BOFactory;
import lk.ijse.supermarket.bo.Custom.ReportBO;
import lk.ijse.supermarket.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReportsFormController {
    public AnchorPane DashBoardContext;
    public Label lblMostMovableItem;
    public Label lblLeastMovableItem;
    public Label lblIncome;
    public Label lblIncomeStatus;

    ReportBO reportBO = (ReportBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REPORT);

    public void initialize(){
        mostMovableItem();
        leastMovableItem();
    }

    private void leastMovableItem() {
        try {
            String code = reportBO.getLeastMovableItem();
            getItemDescription(code, "least");

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void mostMovableItem(){
        try {
            String mostMovableItem = reportBO.getMostMovableItem();
            getItemDescription(mostMovableItem, "most");

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void getItemDescription(String code,String status) {
        try {
            String label = reportBO.getItemDescription(code);

            if(status.equals("most")){
                lblMostMovableItem.setText(label);
            }else if(status.equals("least")){
                lblLeastMovableItem.setText(label);
            }

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnDailyIncomeOnAction(ActionEvent actionEvent) {
        String date= String.valueOf(LocalDate.now());
        try {
            ArrayList<String> dailyIncomeList = reportBO.getDailyIncome(date);

            getIncome(dailyIncomeList,"daily");

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void btnMonthlyIncomeOnAction(ActionEvent actionEvent) {
        try {
            ArrayList<String> monthlyIncomeList = reportBO.getMonthlyIncome();
            getIncome(monthlyIncomeList,"monthly");

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnAnnuallyIncomeOnAction(ActionEvent actionEvent) {
        try {
            ArrayList<String> annuallyIncomeList = reportBO.getAnnuallyIncome();
            getIncome(annuallyIncomeList,"Annually");

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    double total=0.00;
    private void getIncome(ArrayList<String> OrderId, String status) {


        for (String id :OrderId) {
            try {
                double tot= reportBO.getSumOfTotal(id);
                total+=tot;

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (status.equals("daily")){
            lblIncomeStatus.setText("Daily Income");
            lblIncome.setText(String.valueOf("RS : "+total));
        }else if (status.equals("monthly")){
            lblIncomeStatus.setText("Monthly Income");
            lblIncome.setText(String.valueOf("RS : "+total));
        }else if (status.equals("Annually")){
            lblIncomeStatus.setText("Annually Income");
            lblIncome.setText(String.valueOf("RS : "+total));
        }

    }

    public void btnIncomeChartOnAction(ActionEvent actionEvent) {

        try {
            JasperReport compiledReport = (JasperReport) JRLoader.loadObject(getClass().getResource("../reports/IncomeReport.jasper"));
            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, connection);
            JasperViewer.viewReport(jasperPrint,false);


        }catch (JRException |SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
