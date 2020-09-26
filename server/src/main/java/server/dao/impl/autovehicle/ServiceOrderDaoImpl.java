package server.dao.impl.autovehicle;

import server.dao.ServiceOrderDao;
import server.model.autovehicle.ServiceOrder;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ServiceOrderDaoImpl implements ServiceOrderDao {

    private EntityManager entityManager;

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
           return entityManager.find(ServiceOrder.class, id);
    }

    @Override
    public Collection<ServiceOrder> findAll(){

        TypedQuery<ServiceOrder> query = entityManager.createNamedQuery("ServiceOrder.findAll", ServiceOrder.class);
        return query.getResultList();
    }

    @Override
    public List<Integer> findAllServiceOrderIds(){

        TypedQuery<Integer> query = entityManager.createNamedQuery("ServiceOrder.findAllIds",Integer.class);

        return query.getResultList();
    }

    @Override
    public boolean updateServiceOrder(ServiceOrder serviceOrder){

        entityManager.getTransaction().begin();
        entityManager.merge(serviceOrder);
        entityManager.getTransaction().commit();

        return entityManager.getTransaction().getRollbackOnly();
    }

    @Override
    public Optional<ServiceOrder> findOrdersByIds(int id){


        TypedQuery<ServiceOrder> query = entityManager.createNamedQuery("ServiceOrder.findOrderById", ServiceOrder.class);
        query.setParameter("id", id);

        return query.getResultList().stream().findFirst();
    }



        //todo: de rezolvat cu jpql
//    public Collection<ServiceOrder> findByUser(String username){
//
//    }

 //       public Optional<ServiceOrder> findByClientName(String  clientName) {
//
//
//    }



}

