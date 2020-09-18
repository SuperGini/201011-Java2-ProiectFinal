package server.service.autovehicle;

import lib.dto.autovehicle.VehicleDto;
import server.convert.autovehicle.VehicleConvetor;
import server.dao.VehicleDao;
import server.dao.impl.autovehicle.VehicleDaoImpl;
import server.model.autovehicle.Vehicle;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleServiceImpl extends UnicastRemoteObject implements lib.service.VehicleService {

    private VehicleDao vehicleDao;

    public VehicleServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();
        vehicleDao = new VehicleDaoImpl(entityManager);
    }

    @Override
    public boolean createVehicle(VehicleDto vehicleDto){
        Vehicle vehicle = VehicleConvetor.convert(vehicleDto);

        Optional<Vehicle> optionalVehicle = vehicleDao.findBySerialNumber(vehicle.getSerialNumber());

        if(optionalVehicle.isEmpty()){
            vehicleDao.createVehicle(vehicle);

        }

        throw new IllegalArgumentException();
    }

    @Override
    public VehicleDto findById(int id){
        return VehicleConvetor.convert(vehicleDao.findById(id));
    }

    @Override
    public VehicleDto findBySerialNumber(String serialNumeber){
        return vehicleDao.findBySerialNumber(serialNumeber)
                .map(VehicleConvetor::convert)
                .orElseThrow(NoSuchElementException::new);

    }

    @Override
    public Collection<VehicleDto> findAllVehicles(){
        return vehicleDao.findAllVehicles().stream()
                                .map(VehicleConvetor::convert)
                                .collect(Collectors.toSet());
    }

}
