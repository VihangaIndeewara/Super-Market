package lk.ijse.supermarket.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainFormController {
    public AnchorPane MainAnchorPane;

    public void btnAdminOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AdminLoginForm");
    }

    public void btnCashierOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CashierLoginForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) MainAnchorPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
    }
}
