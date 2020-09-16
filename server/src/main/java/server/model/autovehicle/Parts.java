package server.model.autovehicle;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class Parts {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String partName;

    private double price;

    private int count;

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
        Parts parts = (Parts) o;
        return Objects.equals(partName, parts.partName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partName);
    }
}
