package server.model;

import javax.persistence.Entity;

@Entity
public class MechanicalAndBodyAdvisor extends User {


    private String profesion;

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
