package client.controller.autovehicle;

import lib.dto.autovehicle.ServiceOrderDto;
import lib.service.ServiceOrderService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;

public class ServiceOrderController implements ServiceOrderService {

    private ServiceOrderService serviceOrderService;

    public ServiceOrderController() {
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("localhost", 4545);
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

    private static final class SingeltonHolder{
        public static final ServiceOrderController INSTANCE = new ServiceOrderController();
    }

    private static ServiceOrderController getInstance(){
        return SingeltonHolder.INSTANCE;
    }
}
