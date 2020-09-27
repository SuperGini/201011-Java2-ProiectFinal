package server.dao.impl.autovehicle;

import server.model.autovehicle.CountPart;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CountPartDao extends Remote {
    boolean createCount(CountPart countPart) throws RemoteException;

    CountPart findById(int id);
}
