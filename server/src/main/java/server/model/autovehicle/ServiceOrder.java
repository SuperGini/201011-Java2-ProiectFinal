package server.model.autovehicle;

import lib.dto.user.Category;
import server.model.client.Client;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "service_order")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private Category category;


    @ManyToOne
    private Client client;

    public Client getClient() {
        return client;
    }

    @ElementCollection
    @CollectionTable(name = "problems_of_the_car")
    private List<String> carProblems = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "parts_order")
    private Collection<Parts> parts = new HashSet<>();

    @ManyToOne
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public List<String> getCarProblems() {
        return carProblems;
    }

    public void setCarProblems(List<String> carProblems) {
        this.carProblems = carProblems;
    }

    public Collection<Parts> getParts() {
        return parts;
    }

    public void setParts(Collection<Parts> parts) {
        this.parts = parts;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrder that = (ServiceOrder) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
