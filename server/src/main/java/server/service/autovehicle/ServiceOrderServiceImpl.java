package server.service.autovehicle;

import lib.dto.autovehicle.ServiceOrderDto;
import server.convert.autovehicle.ServiceOrderConvertor;
import server.dao.*;
import server.dao.impl.autovehicle.PartDaoImpl;
import server.dao.impl.autovehicle.ServiceOrderDaoImpl;
import server.dao.impl.autovehicle.VehicleDaoImpl;
import server.dao.impl.client.ClientDaoImpl;
import server.dao.impl.user.UserDaoImpl;
import server.model.autovehicle.ServiceOrder;
import server.model.autovehicle.Vehicle;
import server.model.client.Client;
import server.model.user.User;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ServiceOrderServiceImpl  extends UnicastRemoteObject implements lib.service.ServiceOrderService {

    private ServiceOrderDao serviceOrderDao;
    private VehicleDao vehicleDao;
    private UserDao userDao;
    private ClientDao clientDao;
    private PartDao partDao;

    public ServiceOrderServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        serviceOrderDao = new ServiceOrderDaoImpl(entityManager);
        vehicleDao = new VehicleDaoImpl(entityManager);
        userDao = new UserDaoImpl(entityManager);
        clientDao = new ClientDaoImpl(entityManager);
        partDao = new PartDaoImpl(entityManager);

    }

    @Override
    public boolean createServiceOrder(ServiceOrderDto serviceOrderDto) throws RemoteException{
        ServiceOrder serviceOrder = ServiceOrderConvertor.convert(serviceOrderDto);

        Client client = clientDao.findClientById(serviceOrderDto.getIdClient());
        Vehicle vehicle = vehicleDao.findById(serviceOrderDto.getIdVehicul());

        User user = userDao.findByName(serviceOrderDto.getIdUsername())
                                    .orElseThrow(NoSuchElementException::new);

        serviceOrder.setClient(client);
        serviceOrder.setVehicle(vehicle);
        serviceOrder.setUser(user);

        return serviceOrderDao.createServiceOrder(serviceOrder);
    }

    @Override
    public ServiceOrderDto findById(int id) throws RemoteException{
        return ServiceOrderConvertor
                    .convert(serviceOrderDao.findById(id));
    }

    @Override
    public Collection<ServiceOrderDto> findAll() throws RemoteException{
        return serviceOrderDao.findAll()
                .stream()
                .map(ServiceOrderConvertor::convert)
                .collect(Collectors.toList());

    }

    @Override
    public List<Integer> findAllServiceOrderIds() throws RemoteException{
        return serviceOrderDao.findAllServiceOrderIds();

    }

    @Override
    public void updateServiceOrder(ServiceOrderDto serviceOrderDto) throws RemoteException{

        ServiceOrder serviceOrder = ServiceOrderConvertor.convert(serviceOrderDto);

        var parts =serviceOrderDto.getPartsIds().stream()
                .map(s ->partDao.findPartById(s))
                .collect(Collectors.toSet());

                serviceOrder.setParts(parts);

                serviceOrderDao.updateServiceOrder(serviceOrder);


    }
}
