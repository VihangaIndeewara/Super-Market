package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginFormController {
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public AnchorPane LoginAnchorPane;



    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("Indeewara")&&txtPassword.getText().equals("1234")){
               setUi("AdminDashBoardForm");
        }else {
            new Alert(Alert.AlertType.ERROR,"Wrong username or password").show();
        }
    }

    public void backOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("MainForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage=(Stage) LoginAnchorPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
