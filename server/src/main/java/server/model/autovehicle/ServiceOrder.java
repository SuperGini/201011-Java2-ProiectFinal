package server.model.autovehicle;

import server.model.client.Client;
import server.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "service_order")
@NamedQueries({
        @NamedQuery(name ="ServiceOrder.findAll", query = "SELECT s FROM ServiceOrder s"),
        @NamedQuery(name = "ServiceOrder.findAllIds" , query ="SELECT o.id FROM ServiceOrder o" ),
        @NamedQuery(name = "ServiceOrder.findOrderById", query = "SELECT o FROM ServiceOrder o WHERE o.id = :id" ),
        @NamedQuery(name = "ServiceOrder.selectParts", query = "SELECT o.parts FROM ServiceOrder o WHERE o.id = :id")

})


public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;


    private double total;


    public ServiceOrder(int id, double total) {
        this.id = id;
        this.total = total;
    }

    public ServiceOrder() {
    }

    @ManyToOne
    private Client client;

    @ManyToOne
    private User user;


    @ElementCollection
    @CollectionTable(name = "problems_of_the_car")
    private List<String> carProblems = new ArrayList<>();

    @OneToMany(mappedBy = "serviceOrder")
    @Basic(fetch = FetchType.EAGER)
    private List<CountPart> coutParts = new ArrayList<>();


    @OneToMany(mappedBy = "orders")
    @Basic(fetch = FetchType.EAGER)
    private List<Part> parts = new ArrayList<>();




    @ManyToOne
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public List<CountPart> getCoutParts() {
        return coutParts;
    }

    public void setCoutParts(List<CountPart> coutParts) {
        this.coutParts = coutParts;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client getClient() {
        return client;
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

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
