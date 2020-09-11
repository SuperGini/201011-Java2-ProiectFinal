package server;

import server.dao.UserDao;
import server.dao.impl.UserDaoImpl;
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


        UserDao c = new UserDaoImpl(em);

        UserId x = new UserId();

        x.setUserName("bica");
        x.setEmailAdress("asdasd@xx.com");

        User y = new User(x);
        y.setCategory(Category.BODY);
        y.setPhoneNumbers("123");

   //    c.create(y);

        System.out.println(c.findByName("gigel"));

    //    System.out.println(c);
    }
}
