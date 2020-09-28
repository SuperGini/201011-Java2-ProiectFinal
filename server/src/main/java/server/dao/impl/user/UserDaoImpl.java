package server.dao.impl.user;

import server.dao.UserDao;
import server.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
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

    @Override
    public Optional<User> findByEmailAdress(String emailAdress){

        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findByEmail", User.class);
        namedQuery.setParameter("emailAdress", emailAdress );
        return namedQuery.getResultStream().findFirst();
    }

    @Override
    public List<User> findByName2(String userName){

        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findByName", User.class);
        namedQuery.setParameter("userName", userName);
        return namedQuery.getResultList();
    }

    @Override
    public int updatePassword(String newPassword, String userName){
        String jpql ="UPDATE User u SET u.password = :password WHERE u.userId.userName = :userName";

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(jpql);
        query.setParameter("password", newPassword);
        query.setParameter("userName",userName );
        int row = query.executeUpdate();

        entityManager.getTransaction().commit();

        return row;
    }

}
