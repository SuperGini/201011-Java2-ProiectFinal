package server;

import server.model.user.Category;
import server.model.user.User;
import server.model.user.UserId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainServer {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("serviceAuto");
        EntityManager em = emf.createEntityManager();


        UserId x = new UserId();

        x.setUserName("gigel");
        x.setEmailAdress("asdasd@xx.com");

        User y = new User(x);
        y.setCategory(Category.BODY);
        y.setPhoneNumbers("123");

        em.getTransaction().begin();

        em.persist(y);

        em.getTransaction().commit();
    }
}
