package server.model.client;

import server.model.autovehicle.ServiceOrder;
import server.model.autovehicle.Vehicle;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;

    @Embedded
    private Adress adress;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private Collection<Vehicle> vehicles = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Collection<ServiceOrder> serviceOrders = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Collection<Vehicle> vehicles) {
        this.vehicles = vehicles;
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
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
