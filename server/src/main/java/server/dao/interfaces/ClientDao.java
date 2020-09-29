package server.dao.interfaces;

import server.model.client.Client;

import java.util.Optional;

public interface ClientDao {
    Optional<Client> findByName(String name);

    Client findClientById(int id);
}
