package server.model.client;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Person extends Client{

    private String cnp;

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }


    @Override
    public String toString() {
        return " person cnp: " + cnp + " name :" + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(cnp, person.cnp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cnp);
    }
}
