package lk.ijse.supermarket.bo;


import lk.ijse.supermarket.bo.Custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        if (boFactory==null) {
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,MANAGEORDER,PURCHASEORDER,REPORT
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case MANAGEORDER:
                return new ManageOrderBOImpl();
            case PURCHASEORDER:
                return new PurchaseOrderBOImpl();
            case REPORT:
                return new ReportBOImpl();
            default:
                return null;
        }
    }
}
