package lib.service;

import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.Status;
import lib.dto.bill.BillDto;
import lib.dto.bill.TotalPriceDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServiceOrderService extends Remote {

    boolean createServiceOrder(ServiceOrderDto serviceOrderDto) throws RemoteException;

    ServiceOrderDto findById(int id) throws RemoteException;

    boolean updateServiceOrder(ServiceOrderDto serviceOrderDto) throws RemoteException;

    void makeBill(List<PartDto> partsDtos, String path, BillDto billDto, TotalPriceDto totalPriceDto) throws RemoteException;

    int updateServiceOrderStatus(int orderId, Status status) throws RemoteException;

    List<Object[]> findAllServiceOrderIdAndStatus() throws RemoteException;

    int updateTotalPriceAndStatus(int orderId, double totalPrice, Status status) throws RemoteException;

    ServiceOrderDto getPartsAndCarProblems(int id) throws RemoteException;

    ServiceOrderDto getParts(int id) throws RemoteException;
}
