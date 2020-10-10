package lib.service;

import lib.dto.autovehicle.PartDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface PartService extends Remote {

    boolean createPart(PartDto partDto) throws RemoteException;

    PartDto findPartById(int id) throws RemoteException;

    PartDto findPartByName(String name) throws RemoteException;

    Collection<PartDto> findAllParts() throws RemoteException;
}
