package client.controller.autovehicle;

import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.service.ServiceOrderService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.List;

public class ServiceOrderController implements ServiceOrderService {

    private ServiceOrderService serviceOrderService;

    public ServiceOrderController() {

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            serviceOrderService = (ServiceOrderService) registry.lookup("serviceOrder");
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            e.printStackTrace();
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
    public Collection<ServiceOrderDto> findAll(){
        try {
            return serviceOrderService.findAll();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findAllServiceOrderIds(){
        try {
            return serviceOrderService.findAllServiceOrderIds();
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
    public int updateParsAndPartsCount(int orderId) {
        try {
           return serviceOrderService.updateParsAndPartsCount(orderId);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object[]> findOrdersByIds(int id){
        try {
           return serviceOrderService.findOrdersByIds(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PartDto> initInfoOnPartPageAndCreateOrderPage(int orderId){
        try {
            return serviceOrderService.initInfoOnPartPageAndCreateOrderPage(orderId);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int setTotalPriceToOrder(int orderId, double totalPrice){
        try {
            return serviceOrderService.setTotalPriceToOrder(orderId, totalPrice);
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
