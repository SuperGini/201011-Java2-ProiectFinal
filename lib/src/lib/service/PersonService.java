package lib.service;

import lib.dto.client.PersonDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PersonService extends Remote {

    boolean createPerson(PersonDto personDto) throws RemoteException;

    PersonDto findPersonById(int id) throws RemoteException;

    PersonDto findPersonByName(String name) throws RemoteException;


}
