package client.controller;

import lib.dto.BackgroundImageDto;
import lib.service.BackgroundImageService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BackgroundImageController implements BackgroundImageService {

    private BackgroundImageService backgroundImageService;

    private BackgroundImageController() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",4545);
           backgroundImageService =  (BackgroundImageService) registry.lookup("imageService");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public BackgroundImageDto getPicture() {
        try {
            return backgroundImageService.getPicture();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private static final class SingletonHolder{
        public static final BackgroundImageController INSTANCE = new BackgroundImageController();
    }

    public static BackgroundImageController getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
