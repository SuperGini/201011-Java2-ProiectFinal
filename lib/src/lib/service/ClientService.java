package lib.service;

import lib.dto.client.ClientDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {
    ClientDto findClientByName(String name) throws RemoteException;
}
