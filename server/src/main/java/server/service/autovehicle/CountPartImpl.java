package server.service.autovehicle;

import lib.dto.autovehicle.CountPartDto;
import server.convert.autovehicle.CountParConvertor;
import server.dao.impl.autovehicle.CountPartDao;
import server.dao.impl.autovehicle.CountPartDaoImpl;
import server.model.autovehicle.CountPart;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CountPartImpl extends UnicastRemoteObject implements lib.service.CountPartService {

    private CountPartDao countPartDao;

    public CountPartImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        countPartDao = new CountPartDaoImpl(entityManager);
    }


    @Override
    public boolean createCount(CountPartDto countPartDto) throws RemoteException{
        CountPart countPart = CountParConvertor.convet(countPartDto);

//        countPart.setId(countPartDto.getId());
//        countPart.setCountPart(countPartDto.getCountPartDto());
//        countPart.setServiceOrder();

       return countPartDao.createCount(countPart);

    }

    @Override
    public CountPartDto findById(int id) throws RemoteException{
        CountPart countPart = countPartDao.findById(id);

        return CountParConvertor.convert(countPart);
    }
}
