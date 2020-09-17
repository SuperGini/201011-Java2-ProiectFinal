package server.dao;

import server.model.autovehicle.ServiceOrder;

import java.util.Collection;

public interface ServiceOrderDao {
    boolean createServiceOrder(ServiceOrder serviceOrder);

    server.model.autovehicle.ServiceOrder findById(int id);

    Collection<server.model.autovehicle.ServiceOrder> findAllServiceOrders();
}
