package lib.service;

import lib.dto.autovehicle.ServiceOrderDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ServiceOrderService extends Remote {

    boolean createServiceOrder(ServiceOrderDto serviceOrderDto) throws RemoteException;

    ServiceOrderDto findById(int id) throws RemoteException;

    Collection<ServiceOrderDto> findAll() throws RemoteException;
}
