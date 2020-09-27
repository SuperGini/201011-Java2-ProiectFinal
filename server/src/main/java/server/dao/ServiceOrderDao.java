package server.dao;

import server.model.autovehicle.ServiceOrder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ServiceOrderDao {
    boolean createServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder findById(int id);

    Collection<ServiceOrder> findAll();

    List<Integer> findAllServiceOrderIds();

    boolean updateServiceOrder(ServiceOrder serviceOrder);

    Optional<ServiceOrder> findOrdersByIds(int id);

    int updateParsAndPartsCount(int orderId);
}
