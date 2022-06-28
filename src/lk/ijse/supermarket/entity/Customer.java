package lk.ijse.supermarket.entity;

public class Customer {
    private String cusId;
    private String cusName;
    private String cusTitle;
    private String cusAddress;
    private String city;
    private String province;
    private String postalCode;

    public Customer() {
    }

    public Customer(String cusId, String cusName, String cusTitle, String cusAddress, String city, String province, String postalCode) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusTitle = cusTitle;
        this.cusAddress = cusAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public Customer(String cusName, String cusTitle) {
        this.cusName = cusName;
        this.cusTitle = cusTitle;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusTitle() {
        return cusTitle;
    }

    public void setCusTitle(String cusTitle) {
        this.cusTitle = cusTitle;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusTitle='" + cusTitle + '\'' +
                ", cusAddress='" + cusAddress + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
