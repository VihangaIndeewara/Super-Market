package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.bo.BOFactory;
import lk.ijse.supermarket.bo.Custom.PurchaseOrderBO;
import lk.ijse.supermarket.bo.Custom.impl.PurchaseOrderBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.view.TM.CartTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class PurchaseOrderFormController {
    public Label lblOrderId;
    public JFXTextField txtUnitDiscount;
    public JFXTextField txtQty;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public  JFXComboBox<String> cmbCustomerId;
    public TableView<CartTM> tblCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colAmount;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public TableColumn colRemove;
    public Label lblTotal;
    public JFXTextField txtDescription;
    public JFXTextField txtCustomerTitle;
    public JFXTextField txtCustomerName;
    public JFXTextField txtPackSize;
    public TableColumn colSize;
    public JFXButton btnAddToCart;
    public JFXButton btnPlaceOrder;

    String oid;
    LinkedHashMap<JFXTextField, Pattern> map=new LinkedHashMap<>();
    PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PURCHASEORDER);

    public void initialize(){
        generateOrderId();
        getCustomerId();
        getItemCode();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("remove"));

        Pattern qtyPattern =Pattern.compile("^[1-9]{1}[0-9]*$");
        map.put(txtQty,qtyPattern);

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCustomerData(newValue);
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setItemData(newValue);
        });
    }

    private void generateOrderId() {
        try {
            PurchaseOrderBOImpl purchaseOrderBO = new PurchaseOrderBOImpl();
            String newOrderId = purchaseOrderBO.generateOrderId();

            lblOrderId.setText(newOrderId);

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String code) {
        try {
            ArrayList<ItemDTO> itemData = purchaseOrderBO.getItemData(code);

            for (ItemDTO i:itemData) {
                txtDescription.setText(i.getDescription());
                txtPackSize.setText(i.getSize());
                txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
                txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
                txtUnitDiscount.setText(String.valueOf(i.getUnitDiscount()));
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getItemCode() {
        try {
            ObservableList<String> itemCodes = purchaseOrderBO.getItemCode();

            cmbItemCode.setItems(itemCodes);

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerData(String id) {
        try {
            ArrayList<CustomerDTO> data = purchaseOrderBO.getCustomerData(id);

            for (CustomerDTO c:data) {
                txtCustomerName.setText(c.getName());
                txtCustomerTitle.setText(c.getTitle());
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void getCustomerId(){
        try {
            ObservableList<String> customerIds = purchaseOrderBO.getCustomerId();

            cmbCustomerId.setItems(customerIds);

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


    ObservableList<CartTM> obList =FXCollections.observableArrayList();

    public void addToCart(){
        oid=lblOrderId.getText();

        if (txtQty.getText().length()<=0){
            new Alert(Alert.AlertType.WARNING,"Enter Fields").show();
        }else {
            int qty = Integer.parseInt(txtQty.getText());
            double price = Double.parseDouble(txtUnitPrice.getText());
            double discount = Double.parseDouble(txtUnitDiscount.getText());

            double amount = qty * price;
            double totDiscount = qty * discount;
            double tot = amount - totDiscount;

            Button btn = new Button("remove");
            btn.setStyle("-fx-background-color: red");

            CartTM Exist = isExist(cmbItemCode.getValue());

            if (Exist!=null){
                for (CartTM t:obList) {
                    if (t.equals(Exist)){
                        t.setQty(t.getQty()+qty);
                        t.setAmount(t.getAmount()+amount);
                        t.setDiscount(t.getDiscount()+totDiscount);
                        t.setTotal(t.getTotal()+tot);
                    }
                }

            }else  {
                CartTM tm = new CartTM(
                        cmbItemCode.getValue(),
                        txtDescription.getText(),
                        txtPackSize.getText(),
                        qty,
                        price,
                        amount,
                        totDiscount,
                        tot,
                        btn
                );

                btn.setOnAction((event) -> {
                    obList.removeIf(cartTM -> cartTM.getItemCode().equals(tm.getItemCode()));
                    calculateTotal();
                });

                obList.add(tm);
                tblCart.setItems(obList);
            }
        }
        calculateTotal();
        clearTexts();
        btnPlaceOrder.setDisable(false);
        tblCart.refresh();
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        addToCart();
    }

    private CartTM isExist(String code){
        for (CartTM tm:obList) {
            if(tm.getItemCode().equals(code)){
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


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            purchaseOrderBO.setPurchaseOrder(oid, cmbCustomerId.getValue(),obList);
            clear();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void clearTexts(){
        cmbItemCode.getSelectionModel().clearSelection();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtUnitDiscount.clear();
        txtQtyOnHand.clear();
        txtQty.clear();

        txtQty.setFocusColor(Paint.valueOf("#4059a9"));
    }

    private void clear(){
        cmbCustomerId.getSelectionModel().clearSelection();
        txtCustomerName.clear();
        txtCustomerTitle.clear();
        lblTotal.setText("0.00");
        obList.clear();
        btnPlaceOrder.setDisable(true);
        cmbCustomerId.requestFocus();
    }


    public void btnAddNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddNewCustomerForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
    }
}
