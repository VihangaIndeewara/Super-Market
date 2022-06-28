package lk.ijse.supermarket.entity;

public class OrderDetail {
    private String orderId;
    private String itemCode;
    private int orderQty;
    private double unitPrice;
    private double amount;
    private double discount;
    private double total;



    public OrderDetail(String orderId, String itemCode, int orderQty, double unitPrice, double amount, double discount, double total) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public OrderDetail(String itemCode, int orderQty, double unitPrice, double amount, double discount, double total) {
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", orderQty=" + orderQty +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}
