package server.service.client;

import lib.dto.client.ClientDto;
import server.dao.ClientDao;
import server.dao.impl.client.ClientDaoImpl;

import javax.persistence.Persistence;

public class CleintServiceImpl {

    private ClientDao clientDao;

    public CleintServiceImpl() {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        clientDao = new ClientDaoImpl(entityManager);
    }


    public ClientDto findClientByName(String name){



        clientDao.findByName(name);


    }


}
