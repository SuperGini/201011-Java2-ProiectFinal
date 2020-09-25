package lib.service;

import lib.dto.autovehicle.VehicleDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface VehicleService extends Remote {
    boolean createVehicle(VehicleDto vehicleDto) throws RemoteException;

    VehicleDto findById(int id) throws RemoteException;

    VehicleDto findBySerialNumber(String serialNumeber) throws RemoteException;

    Collection<VehicleDto> findAllVehicles() throws RemoteException;

    List<Object[]> findVehicleWithClient(String serialNumber) throws RemoteException;
}
