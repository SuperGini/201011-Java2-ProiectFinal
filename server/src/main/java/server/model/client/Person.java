package server.model.client;

import server.model.autovehicle.ServiceOrder;
import server.model.autovehicle.Vehicle;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name")
public class Person extends Client{

    private String cnp;

    public String getCnp() {
        return cnp;
    }

    public Person() {
    }

    public static class Builder{
        private Person person = new Person();

        public Builder setCnp(String cnp){
            person.setCnp(cnp);
            return this;
        }

        public Builder setName(String name){
            person.setName(name);
            return this;
        }

        public Builder setId(int id){
            person.setId(id);
            return this;
        }

        public Builder setAdress(Adress adress){
            person.setAdress(adress);
            return this;
        }

        public Builder setVehicle(Collection<Vehicle> vehicle){
            person.setVehicles(vehicle);
            return this;
        }

        public Builder setServiceOrder(Collection<ServiceOrder> serviceOrders){
            person.setServiceOrders(serviceOrders);
            return this;
        }

        public Person build(){
            return person;
        }
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
