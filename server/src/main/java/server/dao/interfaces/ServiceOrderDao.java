package server.dao.interfaces;

import lib.dto.autovehicle.Status;
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

    Object initInfoOnPartPageAndCreateOrderPage(int orderId);

    int setTotalPriceToOrder(int orderId, double totalPrice);

    int updateServiceOrderStatus(int orderId, Status status);
}
