package client.controller.client;

import lib.dto.client.ClientDto;
import lib.service.ClientService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientController implements ClientService {

    private final ClientService clientService;

    public ClientController() {
        try {
            Registry registry = LocateRegistry.getRegistry(4545);
            clientService = (ClientService) registry.lookup("clientService");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClientDto findClientByName(String name) {
        try {
            return clientService.findClientByName(name);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder{
        public static final ClientController INSTANCE = new ClientController();
    }

    public static ClientController getInstance(){
       return SingletonHolder.INSTANCE;
    }
}
