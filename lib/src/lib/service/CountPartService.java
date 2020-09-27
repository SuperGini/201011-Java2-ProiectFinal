package lib.service;

import lib.dto.autovehicle.CountPartDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CountPartService extends Remote {
    boolean createCount(CountPartDto countPartDto) throws RemoteException;

    CountPartDto findById(int id)throws RemoteException;
}
