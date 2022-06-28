package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.bo.BOFactory;
import lk.ijse.supermarket.bo.Custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.supermarket.dto.ItemDTO;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManageItemFormController {
    public AnchorPane DashBoardContext;
    public TableView<ItemDTO> tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colSize;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colUnitDiscount;
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtSize;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtUnitDiscount;
    public JFXTextField txtUpdateItemCode;
    public JFXTextField txtUpdateDescription;
    public JFXTextField txtUpdateSize;
    public JFXTextField txtUpdateQty;
    public JFXTextField txtUpdateUnitPrice;
    public JFXTextField txtUpdateUnitDiscount;
    public JFXButton btnSave;

    LinkedHashMap<JFXTextField, Pattern> hashMap=new LinkedHashMap<>();
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize(){

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colUnitDiscount.setCellValueFactory(new PropertyValueFactory<>("unitDiscount"));

        btnSave.setDisable(true);

        Pattern descriptionPattern = Pattern.compile("^[A-z () /]{3,50}$");
        Pattern sizePattern = Pattern.compile("^[A-z0-9 .]{1,7}$");
        Pattern qtyPattern = Pattern.compile("^[1-9]{1}[0-9]*$");
        Pattern unitPricePattern = Pattern.compile("^[1-9]{1}[0-9]*(.00)?$");
        Pattern discountPricePattern = Pattern.compile("^[1-9]{1}[0-9]*(.00)?$");

        hashMap.put(txtDescription,descriptionPattern);
        hashMap.put(txtSize,sizePattern);
        hashMap.put(txtQty,qtyPattern);
        hashMap.put(txtUnitPrice,unitPricePattern);
        hashMap.put(txtUnitDiscount,discountPricePattern);

       tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           setData(newValue);
       });

        loadAllItems();
        generateItemCode();
    }

    private void generateItemCode() {
        try {
            String newCode = itemBO.getNewCode();
            txtItemCode.setText(newCode);

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(ItemDTO newValue) {
        if (newValue!=null) {
            txtUpdateItemCode.setText(newValue.getItemCode());
            txtUpdateDescription.setText(newValue.getDescription());
            txtUpdateSize.setText(newValue.getSize());
            txtUpdateQty.setText(String.valueOf(newValue.getQtyOnHand()));
            txtUpdateUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            txtUpdateUnitDiscount.setText(String.valueOf(newValue.getUnitDiscount()));
        }
    }

    public void loadAllItems(){
        try{
            ObservableList<ItemDTO> allItems = itemBO.getAllItems();
            tblItem.setItems(allItems);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtKeyReleased(KeyEvent keyEvent) {
        validation();

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object respone =validation();

            if (respone instanceof JFXTextField){
                JFXTextField t= (JFXTextField) respone;
                t.requestFocus();
            }else  if(respone instanceof Boolean){
                saveItem();
            }
        }
    }

    public Object validation(){
        for (JFXTextField textField: hashMap.keySet()){
            Pattern pattern = hashMap.get(textField);
            if(pattern.matcher(textField.getText()).matches()){
                accept(textField);
            }else{
                error(textField);
                return textField;
            }
        }
        return true;
    }

    private void error(JFXTextField textField) {
        if(textField.getText().length()>0){
            textField.setFocusColor(Paint.valueOf("red"));
        }
        btnSave.setDisable(true);
    }

    private void accept(JFXTextField textField) {
        textField.setFocusColor(Paint.valueOf("#08b506"));
        btnSave.setDisable(false);
    }

    public void saveItem(){
        ItemDTO i =new ItemDTO(txtItemCode.getText(),txtDescription.getText(),txtSize.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnitDiscount.getText()));

        try {
            itemBO.saveItem(i);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadAllItems();
        clearText();
        generateItemCode();

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        saveItem();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            String description = txtUpdateDescription.getText();
            String size = txtUpdateSize.getText();
            double unitPrice = Double.parseDouble(txtUpdateUnitPrice.getText());
            int qty = Integer.parseInt(txtUpdateQty.getText());
            double discount = Double.parseDouble(txtUpdateUnitDiscount.getText());
            String itemCode = txtUpdateItemCode.getText();

            boolean updateItem = itemBO.updateItem(new ItemDTO(description, size, unitPrice, qty, discount, itemCode));

            if (updateItem) {
                loadAllItems();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearUpdateText();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean deleteItem = itemBO.deleteItem(txtUpdateItemCode.getText());

            if (deleteItem) {
                loadAllItems();
                generateItemCode();
            }

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearUpdateText();
    }

    private void clearText(){
        txtDescription.clear();
        txtUnitPrice.clear();
        txtSize.clear();
        txtQty.clear();
        txtUnitDiscount.clear();

        txtDescription.requestFocus();

        txtDescription.setFocusColor(Paint.valueOf("#4059a9"));
        txtSize.setFocusColor(Paint.valueOf("#4059a9"));
        txtQty.setFocusColor(Paint.valueOf("#4059a9"));
        txtUnitPrice.setFocusColor(Paint.valueOf("#4059a9"));
        txtUnitDiscount.setFocusColor(Paint.valueOf("#4059a9"));
    }

    private  void clearUpdateText(){
        txtUpdateItemCode.clear();
        txtUpdateDescription.clear();
        txtUpdateQty.clear();
        txtUpdateSize.clear();
        txtUpdateUnitPrice.clear();
        txtUpdateUnitDiscount.clear();
    }


}
