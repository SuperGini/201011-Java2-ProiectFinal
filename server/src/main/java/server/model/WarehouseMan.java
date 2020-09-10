package server.model;

import javax.persistence.Entity;

@Entity
public class WarehouseMan extends User {

    private int yearsOfExperience;

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
