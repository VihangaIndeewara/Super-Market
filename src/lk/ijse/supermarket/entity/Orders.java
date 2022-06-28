package lk.ijse.supermarket.entity;

import java.time.LocalDate;


public class Orders {
    private String orderId;
    private LocalDate date;
    private String status;
    private String cusId;

    public Orders() {
    }

    public Orders(String orderId, LocalDate date, String status, String cusId) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.cusId = cusId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", cusId='" + cusId + '\'' +
                '}';
    }
}
