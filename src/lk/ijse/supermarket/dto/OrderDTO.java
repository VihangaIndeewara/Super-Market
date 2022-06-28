package lk.ijse.supermarket.dto;

public class OrderDTO {
    private String orderId;
    private String date;
    private String status;
    private String customerId;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String date, String status, String customerId) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
