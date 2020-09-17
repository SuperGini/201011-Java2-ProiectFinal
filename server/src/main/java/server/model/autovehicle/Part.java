package server.model.autovehicle;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name ="Part.findByName", query = "SELECT p FROM Part p WHERE p.partName = :partName"),
        @NamedQuery(name = "Part.findAll", query = "SELECT p FROM Part p")})
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String partName;

    private double price;

    private int count;

    public Part(String partName, double price, int count) {
        this.partName = partName;
        this.price = price;
        this.count = count;
    }

    public Part() {
    }

    @ManyToMany(mappedBy = "parts")
    private Collection<ServiceOrder> orders = new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Collection<ServiceOrder> getOrders() {
        return orders;
    }

    public void setOrders(Collection<ServiceOrder> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(partName, part.partName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partName);
    }
}
