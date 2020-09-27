package server.service.autovehicle;

import lib.dto.autovehicle.PartDto;
import lib.service.PartService;
import server.convert.autovehicle.PartConvertor;
import server.dao.PartDao;
import server.dao.ServiceOrderDao;
import server.dao.impl.autovehicle.PartDaoImpl;
import server.dao.impl.autovehicle.ServiceOrderDaoImpl;
import server.model.autovehicle.Part;
import server.model.autovehicle.ServiceOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class PartServiceImpl extends UnicastRemoteObject implements PartService {

    private PartDao partDao;
    private ServiceOrderDao serviceOrderDao;

    public PartServiceImpl() throws RemoteException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        partDao = new PartDaoImpl(entityManager);
        serviceOrderDao = new ServiceOrderDaoImpl(entityManager);
    }

    @Override
    public boolean createPart(PartDto partDto) throws RemoteException{
        Part part = PartConvertor.convert(partDto);

        ServiceOrder serviceOrder = serviceOrderDao.findById(partDto.getServiceOrderDto().getId());

        Optional<Part> findPart = partDao.findPartByName(part.getPartName());
            part.setOrders(serviceOrder);

        if(findPart.isEmpty()){

        return partDao.createPart(part);

        }
        throw new IllegalArgumentException();
    }

    @Override
    public PartDto findPartById(int id) throws RemoteException{
       Part part = partDao.findPartById(id);
       return PartConvertor.convert(part);
    }

    @Override
    public PartDto findPartByName(String name) throws RemoteException{

       return partDao.findPartByName(name)
               .map(PartConvertor::convert)
               .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public int increasePartCount(int count, String partName) throws RemoteException {
        return partDao.increasePartCount(count, partName);
    }

    @Override
    public int decreasePartCount(int count, String partName) throws RemoteException{
        return partDao.decreasePartCount(count,partName);
    }

    @Override
    public Collection<PartDto> findAllParts() throws RemoteException {
        return partDao.findAllParts().stream()
                .map(PartConvertor::convert)
                .collect(Collectors.toSet());

    }
}
