package server.model.autovehicle;

import javax.persistence.*;

@Entity
public class CountPart {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;


    private int countPart;

    @ManyToOne(cascade = CascadeType.MERGE)
    private ServiceOrder serviceOrder;

    public int getCountPart() {
        return countPart;
    }

    public void setCountPart(int countPart) {
        this.countPart = countPart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }
}


