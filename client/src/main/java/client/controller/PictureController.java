package client.controller;

import lib.dto.picture.PictureDto;
import lib.service.PictureService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PictureController implements PictureService {

    private PictureService pictureService;

    private PictureController() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",4545);
           pictureService =  (PictureService) registry.lookup("imageService");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public PictureDto getPicture() {
        try {
            return pictureService.getPicture();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private static final class SingletonHolder{
        public static final PictureController INSTANCE = new PictureController();
    }

    public static PictureController getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
