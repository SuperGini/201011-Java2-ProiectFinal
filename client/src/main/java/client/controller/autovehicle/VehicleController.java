package client.controller.autovehicle;

import lib.dto.autovehicle.VehicleDto;
import lib.service.VehicleService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.List;

public class VehicleController implements VehicleService {

    private VehicleService vehicleService;

    public VehicleController() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            vehicleService = (VehicleService) registry.lookup("vehicleService");

        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createVehicle(VehicleDto vehicleDto) {
        try {
            return vehicleService.createVehicle(vehicleDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public VehicleDto findById(int id){
        try {
            return vehicleService.findById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public VehicleDto findBySerialNumber(String serialNumeber){
        try {
            return vehicleService.findBySerialNumber(serialNumeber);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<VehicleDto> findAllVehicles(){
        try {
            return vehicleService.findAllVehicles();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object[]> findVehicleWithClient(String serialNumber){
        try {
           return vehicleService.findVehicleWithClient(serialNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder{
        public final static VehicleController INSTANCE = new VehicleController();
    }

    public static VehicleController getInstance(){
       return SingletonHolder.INSTANCE;
    }
}
