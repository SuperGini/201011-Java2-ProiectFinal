package server.dao;

import server.model.autovehicle.ServiceOrder;

import java.util.Collection;

public interface ServiceOrderDao {
    boolean createServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder findById(int id);

    Collection<ServiceOrder> findAll();
}
