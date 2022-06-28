package lk.ijse.supermarket.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CashierDashBoardFormController {
    public AnchorPane DashBoardContext;
    public AnchorPane AllDashBoardContent;
    public Label lblTime;
    public Label lblDate;

    public void initialize(){
        try {
            setDashBoard();
            loadDateAndTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock=new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime=LocalTime.now();
            lblTime.setText(currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    public void btnPurchaseOrderOnAction(ActionEvent actionEvent) throws IOException {
        setDashBoard();
    }

    public void setDashBoard() throws IOException {
        setUi("PurchaseOrderForm");
    }

    public void btnManageOrderOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ManageOrderForm");
    }

    private void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"));
        DashBoardContext.getChildren().add(parent);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
       Stage stage= (Stage) AllDashBoardContent.getScene().getWindow();
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
       stage.centerOnScreen();
       stage.show();
    }
}
