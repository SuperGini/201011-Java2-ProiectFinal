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
    private Collection<Order> orders = new HashSet<>();


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
