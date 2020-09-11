package server;

import client.controller.BackgroundImageController;
import server.service.BackgroundImageServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {
    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.createRegistry(4545);
            registry.rebind("imageService", new BackgroundImageServiceImpl());

        } catch (RemoteException e) {
            e.printStackTrace();
        }


        System.out.println(BackgroundImageController.getInstance().getPicture().toString());

    }
}
