package lib.service;

import lib.dto.autovehicle.ServiceOrderDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface ServiceOrderService extends Remote {

    boolean createServiceOrder(ServiceOrderDto serviceOrderDto) throws RemoteException;

    ServiceOrderDto findById(int id) throws RemoteException;

    Collection<ServiceOrderDto> findAll() throws RemoteException;

    List<Integer> findAllServiceOrderIds() throws RemoteException;

    boolean updateServiceOrder(ServiceOrderDto serviceOrderDto) throws RemoteException;

    int updateParsAndPartsCount(int orderId) throws RemoteException;

    List<Object[]> findOrdersByIds(int id) throws RemoteException;
}
