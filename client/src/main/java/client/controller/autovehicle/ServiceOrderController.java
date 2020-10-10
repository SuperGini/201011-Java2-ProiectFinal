package client.controller.autovehicle;

import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.Status;
import lib.dto.bill.BillDto;
import lib.dto.bill.TotalPriceDto;
import lib.service.ServiceOrderService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ServiceOrderController implements ServiceOrderService {

    private ServiceOrderService serviceOrderService;

    public ServiceOrderController() {

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            serviceOrderService = (ServiceOrderService) registry.lookup("serviceOrder");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createServiceOrder(ServiceOrderDto serviceOrderDto){
        try {
            return serviceOrderService.createServiceOrder(serviceOrderDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ServiceOrderDto findById(int id){
        try {
            return serviceOrderService.findById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateServiceOrder(ServiceOrderDto serviceOrderDto){
        try {
           return serviceOrderService.updateServiceOrder(serviceOrderDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void makeBill(List<PartDto> partsDtos, String path, BillDto billDto, TotalPriceDto totalPriceDto){
        try {
            serviceOrderService.makeBill(partsDtos, path, billDto, totalPriceDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateServiceOrderStatus(int orderId, Status status){
        try {
           return serviceOrderService.updateServiceOrderStatus(orderId, status);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object[]> findAllServiceOrderIdAndStatus(){
        try {
           return serviceOrderService.findAllServiceOrderIdAndStatus();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateTotalPriceAndStatus(int orderId, double totalPrice, Status status){
        try {
           return serviceOrderService.updateTotalPriceAndStatus(orderId, totalPrice, status);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingeltonHolder{
        public static final ServiceOrderController INSTANCE = new ServiceOrderController();
    }

    public static ServiceOrderController getInstance(){
        return SingeltonHolder.INSTANCE;
    }


}
