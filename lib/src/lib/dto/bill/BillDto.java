package lib.dto.bill;

import java.io.Serializable;

public class BillDto implements Serializable {

    private String orderId;

    private String clientName;

    private String brand;

    private String serialNumber;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return  "Order: .... " + orderId + "\n" +
                "Client:....." + clientName + "\n" +
                "Brand:......" + brand + "\n" +
                "Serial:....." + serialNumber + "\n" + "\n" +"\n" + "\n" +
                "Part name:" + "                                   " + "count" + "                                   " + "price";


    }
}
