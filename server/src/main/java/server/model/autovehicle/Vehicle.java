package server.model.autovehicle;


import server.model.client.Client;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String vehicleName;

    private String serialNumber;

    public Vehicle(String vehicleName, String serialNumber) {
        this.vehicleName = vehicleName;
        this.serialNumber = serialNumber;
    }

    public Vehicle() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @OneToMany(mappedBy = "vehicle")
    private Collection<ServiceOrder> serviceOrders  = new HashSet<>();

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
//
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
//
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Collection<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(serialNumber, vehicle.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber);
    }
}
