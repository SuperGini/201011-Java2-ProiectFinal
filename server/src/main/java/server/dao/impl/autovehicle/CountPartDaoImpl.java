package server.dao.impl.autovehicle;

import server.model.autovehicle.CountPart;

import javax.persistence.EntityManager;

public class CountPartDaoImpl implements CountPartDao {

    private EntityManager entityManager;

    public CountPartDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean createCount(CountPart countPart){
        entityManager.getTransaction().begin();
        entityManager.persist(countPart);
        entityManager.getTransaction().commit();
        return entityManager.getTransaction().getRollbackOnly();
    }

    @Override
    public CountPart findById(int id){
        return entityManager.find(CountPart.class, id);
    }


}
