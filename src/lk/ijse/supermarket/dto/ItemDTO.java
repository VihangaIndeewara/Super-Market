package lk.ijse.supermarket.dto;

public class ItemDTO {
    private String itemCode;
    private String description;
    private String size;
    private Double unitPrice;
    private int qtyOnHand;
    private double unitDiscount;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode, String description, String size, Double unitPrice, int qtyOnHand, double unitDiscount) {
        this.itemCode = itemCode;
        this.description = description;
        this.size = size;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.unitDiscount = unitDiscount;
    }
    public ItemDTO( String description, String size,int qtyOnHand, Double unitPrice,  double unitDiscount) {
        this.description = description;
        this.size = size;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.unitDiscount = unitDiscount;
    }



    public ItemDTO(String description, String size, double unitPrice, int qtyOnHand, double unitDiscount, String itemCode) {
        this.itemCode = itemCode;
        this.description = description;
        this.size = size;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.unitDiscount = unitDiscount;
    }


    public ItemDTO(String description, String size) {
        this.description = description;
        this.size = size;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitDiscount() {
        return unitDiscount;
    }

    public void setUnitDiscount(double unitDiscount) {
        this.unitDiscount = unitDiscount;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                ", unitDiscount=" + unitDiscount +
                '}';
    }
}
