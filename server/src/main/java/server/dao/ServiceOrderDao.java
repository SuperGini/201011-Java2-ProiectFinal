package server.dao;

import server.model.autovehicle.ServiceOrder;

import java.util.Collection;
import java.util.List;

public interface ServiceOrderDao {
    boolean createServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder findById(int id);

    Collection<ServiceOrder> findAll();

    List<Integer> findAllServiceOrderIds();
}
