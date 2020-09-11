package server.dao.impl;

import server.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class UderDaoImpl implements server.dao.UderDao {

    private EntityManager entityManager;

    public UderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<User> findByName(String userName){

        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findByName", User.class);
        namedQuery.setParameter("userName", userName);
        return namedQuery.getResultStream().findFirst();

    }


    

}
