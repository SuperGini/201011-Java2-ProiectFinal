package server.dao.impl.user;

import server.dao.interfaces.UserDao;
import server.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    @Override
    public Optional<User> findByEmailAdress(String emailAdress){

        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findByEmail", User.class);
        namedQuery.setParameter("emailAdress", emailAdress );
        return namedQuery.getResultStream().findFirst();
    }

    @Override
    public int updatePassword(String newPassword, User user){
        String jpql ="UPDATE User u SET u.password = :password WHERE u.userId.userName = :userName";

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(jpql);
        query.setParameter("password", newPassword);
        query.setParameter("userName",user.getUserId().getUserName() );
        int row = query.executeUpdate();
        User x =  entityManager.find(User.class, user.getUserId()); //-> fac find dupa id sa bag instanta in context
        entityManager.refresh(x); // -> fac refresh la instanta ca sa iau datele din baza ca altfel raman naibii logat
                                // afara cand dau restart la client (adica merge dar cu parola veche)

        entityManager.getTransaction().commit();
        return row;
    }


    @Override
    public boolean addPhoneNumber(User u, String phoneNumber){

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, u.getUserId());
        user.getPhoneNumber().add(phoneNumber);
        entityManager.getTransaction().commit();

        return entityManager.getTransaction().getRollbackOnly();
    }

    @Override
    public boolean updatePhoneNumber(User u){

        entityManager.getTransaction().begin();
        entityManager.merge(u);
        entityManager.getTransaction().commit();

        return entityManager.getTransaction().getRollbackOnly();
    }
}
