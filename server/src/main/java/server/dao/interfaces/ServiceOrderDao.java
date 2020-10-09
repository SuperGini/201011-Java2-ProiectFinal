package server.dao.interfaces;

import lib.dto.autovehicle.Status;
import server.model.autovehicle.ServiceOrder;

import java.util.List;

public interface ServiceOrderDao {
    boolean createServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder findById(int id);

    boolean updateServiceOrder(ServiceOrder serviceOrder);

 //   int setTotalPriceToOrder(int orderId, double totalPrice);

    int updateServiceOrderStatus(int orderId, Status status);

    List<Object[]> findAllServiceOrderIdAndStatus();

    int updateTotalPriceAndStatus(int orderId, double totalPrice, Status status);
}
