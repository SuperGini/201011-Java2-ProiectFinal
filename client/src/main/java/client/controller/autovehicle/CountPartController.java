package client.controller.autovehicle;

import lib.dto.autovehicle.CountPartDto;
import lib.service.CountPartService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CountPartController implements CountPartService {

    private CountPartService countPartService;

    public CountPartController() {

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            countPartService = (CountPartService) registry.lookup("CountPartService");

        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean createCount(CountPartDto countPartDto) {
        try {
            return countPartService.createCount(countPartDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public CountPartDto findById(int id) {
        try {
           return countPartService.findById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingeltonHolder{
        public static final CountPartController INSTANCE = new CountPartController();
    }

    public static CountPartController getInstance(){
        return SingeltonHolder.INSTANCE;
    }
}
