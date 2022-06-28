package lk.ijse.supermarket.dto;

public class OrderDetailDTO {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private double amount;
    private double discount;
    private double total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderId, String itemCode, int qty, double unitPrice, double amount, double discount, double total) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public OrderDetailDTO(String itemCode, int qty, double unitPrice, double amount, double discount, double total) {
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public OrderDetailDTO(int qty, double amount, double discount, double total, String orderId) {
        this.orderId = orderId;
        this.qty = qty;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public OrderDetailDTO(String itemCode, int qty) {
        this.itemCode = itemCode;
        this.qty = qty;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
        return "OrderDetailDTO{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}
