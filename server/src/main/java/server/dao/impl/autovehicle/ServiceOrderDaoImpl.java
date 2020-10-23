package server.dao.impl.autovehicle;

import lib.dto.autovehicle.Status;
import server.dao.interfaces.ServiceOrderDao;
import server.model.autovehicle.ServiceOrder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceOrderDaoImpl implements ServiceOrderDao {

    private final EntityManager entityManager;

    public ServiceOrderDaoImpl(EntityManager entityManager) {
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
           ServiceOrder serviceOrder = entityManager.find(ServiceOrder.class, id);
           entityManager.refresh(serviceOrder);
           return serviceOrder;
    }

    @Override
    public boolean updateServiceOrder(ServiceOrder serviceOrder){

        entityManager.getTransaction().begin();
        entityManager.merge(serviceOrder);
        entityManager.getTransaction().commit();

        return entityManager.getTransaction().getRollbackOnly();
    }

    @Override
    public int updateServiceOrderStatus(int orderId, Status status){
        String jpql = "UPDATE ServiceOrder s SET s.status = :status WHERE s.id = :id";

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(jpql)
                .setParameter("status", status)
                .setParameter("id", orderId);

        int row = query.executeUpdate();
        entityManager.getTransaction().commit();

        return row;
    }

    @Override
    public List<Object[]> findAllServiceOrderIdAndStatus(){
        String jpql = "SELECT s.id, s.status FROM ServiceOrder AS s";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

        return query.getResultList();

    }

    @Override
    public int updateTotalPriceAndStatus(int orderId, double totalPrice, Status status){
        String jpql = "UPDATE ServiceOrder s SET s.status = :status, s.total = :totalPrice  WHERE s.id = :id";

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(jpql)
                    .setParameter("status", status)
                    .setParameter("totalPrice", totalPrice)
                    .setParameter("id", orderId);

        int row = query.executeUpdate();
        entityManager.getTransaction().commit();

        return row;
    }

    @Override
    public void detachServiceOrder(int id){
        entityManager.getTransaction().begin();
        var order = entityManager.find(ServiceOrder.class, id);
        entityManager.detach(order);
        entityManager.getTransaction().commit();
    }


    @Override
    public ServiceOrder getPartsAndCarProblems(int id){
        String jpql = "SELECT s FROM ServiceOrder s LEFT JOIN FETCH s.parts WHERE s.id = :id";
        String jpql2 = "SELECT s FROM ServiceOrder s LEFT JOIN FETCH s.carProblems p WHERE s IN :serviceOrder ";

        //https://vladmihalcea.com/hibernate-multiplebagfetchexception/
        var serviceOrder = entityManager.createQuery(jpql, ServiceOrder.class)
                                     .setParameter("id", id)
                                     .getSingleResult();

                         serviceOrder = entityManager.createQuery(jpql2, ServiceOrder.class)
                                      .setParameter("serviceOrder",serviceOrder )
                                      .getSingleResult();

        return serviceOrder;
    }


    @Override
    public ServiceOrder getParts(int id){
        String jpql = "SELECT s FROM ServiceOrder s LEFT JOIN FETCH s.parts p WHERE s.id = :id";

        TypedQuery<ServiceOrder> query = entityManager.createQuery(jpql, ServiceOrder.class);
                                 query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public ServiceOrder findById2(int id){
        String jpql = "SELECT s FROM ServiceOrder s WHERE s.id = : id";

        return entityManager.createQuery(jpql, ServiceOrder.class)
                            .setParameter("id", id)
                            .getSingleResult();
    }
}

