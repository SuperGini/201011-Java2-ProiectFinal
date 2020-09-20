package server.model.client;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.util.Objects;

@Entity
@NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name")
public class Company extends Client{

    private String cui;


    public static class Builder{
        private Company company = new Company();

        public Builder setCui(String cui){
            company.cui = cui;
            return this;
        }

        public Builder setName(String name){
            company.setName(name);
            return this;
        }

        public Builder setAdress(Adress adress){
          company.setAdress(adress);
          return this;
        }

        public Builder setId(int id){
            company.setId(id);
            return this;
        }

        public Company build(){
            return company;
        }

    }

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
