package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.bo.BOFactory;
import lk.ijse.supermarket.bo.Custom.ManageOrderBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.dto.OrderDetailDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import lk.ijse.supermarket.view.TM.CartTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManageOrderFormController {
    public TableView<CartTM> tblCartOrder;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colAmount;
    public TableColumn colDiscount;
    public TableColumn colTotal;

    public JFXButton btnPrintBill;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;

    public JFXButton btnAddToCart;
    public JFXTextField txtDescription;

    public ListView<String> listOrders;
    public JFXTextField txtSize;
    public TableColumn colSize;
    public JFXTextField txtDiscount;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtQtyOnHand;
    public Label lblTotal;
    public JFXButton btnClear;

    String oid="";
    LinkedHashMap<JFXTextField, Pattern> map=new LinkedHashMap<>();

    ManageOrderBO manageOrderBO = (ManageOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGEORDER);

    public void initialize(){
        setItemCode();
        processOrders();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        Pattern qtyPattern =Pattern.compile("^[1-9]{1}[0-9]*$");
        map.put(txtQty,qtyPattern);

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setItemData(newValue);
        });

        listOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            oid=newValue;
            clearTexts();
            obList.clear();
            setTableData(newValue);
        });
    }

    private void setItemData(String code) {
        try {
            ArrayList<ItemDTO> itemDetails = manageOrderBO.getItemDetails(code);

            for (ItemDTO i:itemDetails) {
                txtDescription.setText(i.getDescription());
                txtSize.setText(i.getSize());
                txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
                txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
                txtDiscount.setText(String.valueOf(i.getUnitDiscount()));

            }


        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setItemCode() {
       try {
           ObservableList<String> itemCodes = manageOrderBO.getItemCodes();
           cmbItemCode.setItems(itemCodes);

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    ObservableList<CartTM> obList =FXCollections.observableArrayList();

    private void setTableData(String id) {
        btnPrintBill.setDisable(false);

        Button btn = new Button("Remove");
        btn.setStyle("-fx-background-color: red");

        try {
            ArrayList<OrderDetailDTO> details = manageOrderBO.getOrderDetails(id);

            for (OrderDetailDTO rst : details) {
                ArrayList<ItemDTO> itemInfo = manageOrderBO.getItemInformation(rst.getItemCode());

                String description = "";
                String packSize = "";

                for (ItemDTO i : itemInfo) {
                    description = i.getDescription();
                    packSize = i.getSize();
                }

            CartTM t = new CartTM(
                    rst.getItemCode(),
                    description,
                    packSize,
                    rst.getQty(),
                    rst.getUnitPrice(),
                    rst.getAmount(),
                    rst.getDiscount(),
                    rst.getTotal()
            );
            obList.add(t);
        }
            }catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
            tblCartOrder.setItems(obList);
            calculateTotal();
        }

    

    public void processOrders(){
        try {
            ArrayList<String> processOrders = manageOrderBO.getProcessingOrders();
            listOrders.getItems().addAll(processOrders);

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void txtKeyReleased(KeyEvent keyEvent) throws Exception {
        validation();

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object responce = validation();

            if (responce instanceof JFXTextField){
                JFXTextField t= (JFXTextField) responce;
                t.requestFocus();
            }
        }
    }

    public Object validation(){
        for (JFXTextField t: map.keySet()){
            Pattern pattern = map.get(t);
            if (pattern.matcher(t.getText()).matches()){
                accept(t);
            }else {
                error(t);
                return t;
            }
        }
        return null;
    }

    private void error(JFXTextField textField) {
        if(textField.getText().length()>0){
            textField.setFocusColor(Paint.valueOf("red"));
        }
        btnAddToCart.setDisable(true);
    }

    private void accept(JFXTextField textField) {
        textField.setFocusColor(Paint.valueOf("#08b506"));

        txtQty.getText();

        if(Integer.parseInt(txtQty.getText()) >Integer.parseInt(txtQtyOnHand.getText())){
            btnAddToCart.setDisable(true);
            textField.setFocusColor(Paint.valueOf("red"));
        }else {
            btnAddToCart.setDisable(false);
            textField.setFocusColor(Paint.valueOf("#08b506"));
        }
    }

    public void addToCart(){

        int qty= Integer.parseInt(txtQty.getText());
        double unitPrice= Double.parseDouble(txtUnitPrice.getText());
        double amount=qty*unitPrice;
        double discount=qty*Double.parseDouble(txtDiscount.getText());
        double tot=amount-discount;

        Button btn=new Button("Remove");
        btn.setStyle("-fx-background-color: red");


        CartTM exists = isExists(cmbItemCode.getValue());

        if (exists!=null){
            for (CartTM tm:obList) {
                if (tm.equals(exists)) {
                    tm.setQty(tm.getQty() + qty);
                    tm.setAmount(tm.getAmount() + amount);
                    tm.setDiscount(tm.getDiscount()+discount);
                    tm.setTotal(tm.getTotal()+tot);
                }
            }
        }else{

            CartTM t =new CartTM(
                    cmbItemCode.getValue(),
                    txtDescription.getText(),
                    txtSize.getText(),
                    qty,
                    unitPrice,
                    amount,
                    discount,
                    tot
            );
            obList.add(t);
        }

        tblCartOrder.refresh();
        calculateTotal();

    }


    public void btnAddToCartOnAction(ActionEvent actionEvent) {
            addToCart();
    }

    private CartTM isExists(String Code) {
        for (CartTM tm:obList) {
           if(tm.getItemCode().equals(Code)){
               return tm;
           }
        }
        return null;
    }

    private void calculateTotal(){
        double total=0;

        for (CartTM tm:obList) {
            total+=tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    public void clearTexts(){
        cmbItemCode.getSelectionModel().clearSelection();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDiscount.clear();
        txtSize.clear();
        txtQty.clear();

        txtQty.setFocusColor(Paint.valueOf("#4059a9"));
    }


    public void btnPrintBillOnAction(ActionEvent actionEvent) {



        LinkedHashMap map =new LinkedHashMap();

        try{
         JasperReport compiledReport =(JasperReport) JRLoader.loadObject(getClass().getResource("../reports/Bill.jasper"));

         map.put("id",oid);
         map.put("date",String.valueOf(LocalDate.now()));
         map.put("time",String.valueOf(LocalTime.now()));
         map.put("tot",Double.parseDouble(lblTotal.getText()));


         JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, map, new JRBeanCollectionDataSource(obList));
         JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }

        updateOrderStatus(oid);
        updateOrderDetails(oid);
        btnPrintBill.setDisable(true);
    }

    private void updateOrderDetails(String oid) {

        ArrayList<OrderDetailDTO> details=new ArrayList<>();

        for (CartTM t:obList) {
           details.add(new OrderDetailDTO(
                   t.getItemCode(),
                   t.getQty(),
                   t.getUnitPrice(),
                   t.getAmount(),
                   t.getDiscount(),
                   t.getTotal()
           ));

        }

        for (OrderDetailDTO t:details) {
            try {
                manageOrderBO.updateOrderDetails(new OrderDetailDTO(t.getQty(), t.getAmount(), t.getDiscount(), t.getTotal(), t.getOrderId()));

            }catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateOrderStatus(String id) {
        String status="Provided";
        try {
            boolean b = manageOrderBO.updateOrderStatus(status, id);

            if (b) {
                listOrders.getItems().clear();
                processOrders();
            }

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnCancelOrderONAction(ActionEvent actionEvent) {
        deleteOrder();
    }

    private void deleteOrder() {
        try {
            boolean delete = manageOrderBO.deleteOrder(oid);

            if (delete) {
                new Alert(Alert.AlertType.CONFIRMATION,"Order Cancelled").show();
                listOrders.getItems().clear();
                processOrders();
                obList.clear();
                lblTotal.setText("0.00");
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        listOrders.getSelectionModel().clearSelection();
        lblTotal.setText("0.00");
        obList.clear();
    }
}
