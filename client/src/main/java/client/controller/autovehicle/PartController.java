package client.controller.autovehicle;

import lib.dto.autovehicle.PartDto;
import lib.service.PartService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;

public class PartController implements PartService {

    private PartService partService;

    public PartController() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            partService = (PartService) registry.lookup("partService");
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean createPart(PartDto partDto){
        try {
            return partService.createPart(partDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public PartDto findPartById(int id){
        try {
            return partService.findPartById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public PartDto findPartByName(String name){
        try {
            return partService.findPartByName(name);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int increasePartCount(int count, String partName) {
        try {
           return partService.increasePartCount(count, partName);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int decreasePartCount(int count, String partName){
        try {
          return  partService.decreasePartCount(count, partName);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    @Override
    public Collection<PartDto> findAllParts(){
        try {
            return partService.findAllParts();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder{
        public final static PartController INSTANCE = new PartController();
    }

    public static PartController getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
