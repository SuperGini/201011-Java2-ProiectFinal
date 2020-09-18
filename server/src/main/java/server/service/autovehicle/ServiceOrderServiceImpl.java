package server.service.autovehicle;

import lib.dto.autovehicle.ServiceOrderDto;
import server.convert.autovehicle.ServiceOrderConvertor;
import server.dao.ServiceOrderDao;
import server.dao.impl.autovehicle.ServiceOrderDaoImpl;
import server.model.autovehicle.ServiceOrder;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.stream.Collectors;

public class ServiceOrderServiceImpl  extends UnicastRemoteObject implements lib.service.ServiceOrderService {

    private ServiceOrderDao serviceOrderDao;

    public ServiceOrderServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        serviceOrderDao = new ServiceOrderDaoImpl(entityManager);
    }

    @Override
    public boolean createServiceOrder(ServiceOrderDto serviceOrderDto){
        ServiceOrder serviceOrder = ServiceOrderConvertor.convert(serviceOrderDto);

        return serviceOrderDao.createServiceOrder(serviceOrder);
    }

    @Override
    public ServiceOrderDto findById(int id){
        return ServiceOrderConvertor
                    .convert(serviceOrderDao.findById(id));
    }

    @Override
    public Collection<ServiceOrderDto> findAll(){
        return serviceOrderDao.findAll()
                .stream()
                .map(ServiceOrderConvertor::convert)
                .collect(Collectors.toList());

    }

}
