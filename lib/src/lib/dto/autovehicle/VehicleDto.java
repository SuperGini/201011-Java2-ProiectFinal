package lib.dto.autovehicle;

import java.io.Serializable;

public class VehicleDto implements Serializable {

    private int id;

    private String vehicleName;

    private String serialNumber;

    public VehicleDto(int id, String vehicleName, String serialNumber) {
        this.id = id;
        this.vehicleName = vehicleName;
        this.serialNumber = serialNumber;
    }

    public VehicleDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
