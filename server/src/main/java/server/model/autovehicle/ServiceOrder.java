package server.model.autovehicle;

import lib.dto.autovehicle.Status;
import server.model.client.Client;
import server.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "service_order")
@NamedQueries({  //nu le mai folosesc -> au fost folosite mai mult pt verificare sintaxa jpql
       @NamedQuery(name ="ServiceOrder.findPartsAndcarProblems", query = "SELECT s FROM ServiceOrder s JOIN FETCH s.carProblems p WHERE s IN :serviceOrder "),
        @NamedQuery(name = "ServiceOrder.findOrderById", query = "SELECT o FROM ServiceOrder o WHERE o.id = :id" )

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;


    @ElementCollection
    @CollectionTable(name = "problems_of_the_car")
    private List<String> carProblems = new ArrayList<>();


    @OneToMany(mappedBy = "orders")
    private List<Part> parts = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;


    public static class Builder{
        private ServiceOrder serviceOrder = new ServiceOrder();

        public Builder setId(int id){
            serviceOrder.id = id;
            return this;
        }

        public Builder setTotal(double total){
            serviceOrder.total = total;
            return this;
        }

        public Builder setClient (Client client){
            serviceOrder.client = client;
            return this;
        }

        public Builder setUser(User user){
            serviceOrder.user = user;
            return this;
        }

        public Builder setStatus(Status status){
            serviceOrder.status = status;
            return this;
        }

        public Builder setCarProblems(List<String> carProblems){
            serviceOrder.carProblems = carProblems;
            return this;
        }

        public Builder setParts(List<Part> parts){
            serviceOrder.parts = parts;
            return this;
        }

        public Builder setVehicle(Vehicle vehicle){
            serviceOrder.vehicle = vehicle;
            return this;
        }

        public ServiceOrder build(){
            return serviceOrder;
        }

    }


    public Vehicle getVehicle() {
        return vehicle;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
