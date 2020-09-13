package server.dao.impl;

import server.dao.UserDao;
import server.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean create(User user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
      return  entityManager.getTransaction().getRollbackOnly();
    }

    @Override
    public Optional<User> findByName(String userName){

        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findByName", User.class);
        namedQuery.setParameter("userName", userName);
        return namedQuery.getResultStream().findFirst();

    }


    

}
