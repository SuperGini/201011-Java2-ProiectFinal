package client.controller.client;

import lib.dto.client.PersonDto;
import lib.service.PersonService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PersonController implements PersonService {

    private PersonService personService;

    private PersonController() {

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            personService = (PersonService) registry.lookup("personService");

        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean createPerson(PersonDto personDto){
        try {
            return personService.createPerson(personDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public PersonDto findPersonById(int id){
        try {
            return personService.findPersonById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public PersonDto findPersonByName(String name){
        try {
            return personService.findPersonByName(name);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public PersonDto findPerson(String name) {
//        try {
//           return personService.findPerson(name);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//
//    }

    private  static final class SingletonHolder{
        public  static  final  PersonController INSTANCE = new PersonController();
    }

    public static PersonController getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
