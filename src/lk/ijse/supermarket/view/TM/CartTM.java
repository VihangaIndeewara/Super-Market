package lk.ijse.supermarket.view.TM;

import javafx.scene.control.Button;

public class CartTM {
    private String itemCode;
    private String description;
    private String size;
    private int qty;
    private double unitPrice;
    private double amount;
    private double discount;
    private double total;
    private Button remove;

    public CartTM() {
    }

    public CartTM(String itemCode, String description, String size, int qty, double unitPrice, double amount, double discount, double total, Button remove) {
        this.itemCode = itemCode;
        this.description = description;
        this.size = size;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
        this.remove = remove;
    }

    public CartTM(String itemCode,String description,String size, int qty, double unitPrice, double amount, double discount, double total) {
        this.itemCode = itemCode;
        this.description = description;
        this.size = size;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.discount = discount;
        this.total = total;

    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public Button getRemove() {
        return remove;
    }

    public void setRemove(Button remove) {
        this.remove = remove;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", discount=" + discount +
                ", total=" + total +
                ", remove=" + remove +
                '}';
    }
}
