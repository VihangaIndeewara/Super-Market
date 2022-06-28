package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.bo.Custom.CustomerBO;
import lk.ijse.supermarket.bo.Custom.impl.CustomerBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.supermarket.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddNewCustomerFormController {
    public AnchorPane NewCustomerAnchorPane;


    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtTitle;
    public JFXTextField txtAddress;
    public JFXTextField txtProvince;
    public JFXTextField txtCity;
    public JFXTextField txtPostalCode;
    public JFXButton btnSave;

    LinkedHashMap<JFXTextField, Pattern> hashMap=new LinkedHashMap<>();
    CustomerBO customerBO = new CustomerBOImpl();

    public void initialize(){
        generateId();


        Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
        Pattern titlePattern = Pattern.compile("^[A-z ]{3,10}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 / , ]{4,50}$");
        Pattern cityPattern = Pattern.compile("^[A-z ]{3,20}$");
        Pattern provincePattern = Pattern.compile("^[A-z ]{3,20}$");
        Pattern postalCodePattern = Pattern.compile("^[A-z0-9 ]{3,10}$");



        btnSave.setDisable(true);

        hashMap.put(txtName,namePattern);
        hashMap.put(txtTitle,titlePattern);
        hashMap.put(txtAddress,addressPattern);
        hashMap.put(txtCity,cityPattern);
        hashMap.put(txtProvince,provincePattern);
        hashMap.put(txtPostalCode,postalCodePattern);

    }

    public void generateId(){
        try{
            String newId = customerBO.getNewId();
            txtId.setText(newId);

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void txtKetReleased(KeyEvent keyEvent) {
        validation();

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object respone =validation();

            if (respone instanceof JFXTextField){
                JFXTextField t= (JFXTextField) respone;
                t.requestFocus();
            }else  if(respone instanceof Boolean){
                saveCustomer();
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

    private void saveCustomer() {
        CustomerDTO c= new CustomerDTO(txtId.getText(),txtName.getText(),txtTitle.getText(),txtAddress.getText(),txtProvince.getText(),txtCity.getText(),txtPostalCode.getText());

        try{
            boolean saveCustomer = customerBO.saveCustomer(c);

            if (saveCustomer) {
                new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                clearText();
                generateId();
            }

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
       saveCustomer();
    }

    private void clearText(){
        txtName.clear();
        txtTitle.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();

        txtName.requestFocus();

        txtName.setFocusColor(Paint.valueOf("#4059a9"));
        txtTitle.setFocusColor(Paint.valueOf("#4059a9"));
        txtAddress.setFocusColor(Paint.valueOf("#4059a9"));
        txtCity.setFocusColor(Paint.valueOf("#4059a9"));
        txtProvince.setFocusColor(Paint.valueOf("#4059a9"));
        txtPostalCode.setFocusColor(Paint.valueOf("#4059a9"));
       }

}
