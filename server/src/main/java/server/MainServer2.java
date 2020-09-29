package server;

import server.model.sound.Sound;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;

public class MainServer2 {

    public static void main(String[] args) throws RemoteException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("serviceAuto");
        EntityManager em = emf.createEntityManager();

        Path y = Paths.get("./server/src/main/resources/sounds/sound4.wav");
        Sound sound = new Sound();

       
    }







}
