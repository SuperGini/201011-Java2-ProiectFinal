package server.dao.impl.client;

import server.model.client.Client;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class ClientDaoImpl implements server.dao.ClientDao {

    private EntityManager entityManager;

    public ClientDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Optional<Client> findByName(String name){
        String jpql = "SELECT c FROM Client c WHERE c.name = :name";

        TypedQuery<Client> query = entityManager.createQuery(jpql,Client.class);
        query.setParameter("name", name);

        return query.getResultStream().findFirst();


    }

}
