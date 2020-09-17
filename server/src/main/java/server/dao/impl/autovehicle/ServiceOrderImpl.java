package server.dao.impl.autovehicle;

import server.dao.ServiceOrderDao;
import server.model.autovehicle.ServiceOrder;

import javax.persistence.EntityManager;
import java.util.Collection;

public class ServiceOrderImpl implements ServiceOrderDao {

    private EntityManager entityManager;

    public ServiceOrderImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean createServiceOrder(ServiceOrder serviceOrder){
        entityManager.getTransaction().begin();
        entityManager.persist(serviceOrder);
        entityManager.getTransaction().commit();

        return entityManager.getTransaction().getRollbackOnly();
    }

    @Override
    public ServiceOrder findById(int id){
       return entityManager.find(ServiceOrder.class, id);

    }

    @Override
    public Collection<ServiceOrder> findAllServiceOrders(){
        String sql = "SELECT s FROM ServiceOrder s";
        var query = entityManager.createQuery(sql, ServiceOrder.class);

        return query.getResultList();
    }


        //todo: de implementat metoda cnad e gata si gui-ul
//    public Optional<ServiceOrder> findByClientName(String  clientName){
//
//
//    }
}
