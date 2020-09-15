package server.model.client;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Company extends Client{

    private String cui;

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    @Override
    public String toString() {
        return "company cui: " + cui + " name: " + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return Objects.equals(cui, company.cui);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cui);
    }
}
