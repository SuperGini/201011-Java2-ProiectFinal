package lib.dto.autovehicle;

import lib.dto.client.ClientDto;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

public class VehicleDto implements Serializable {

    private int id;

    private String vehicleName;

    private String serialNumber;


    private ClientDto client;


    private Collection<ServiceOrderDto> serviceOrders  = new HashSet<>();

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

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public Collection<ServiceOrderDto> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Collection<ServiceOrderDto> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }
}
