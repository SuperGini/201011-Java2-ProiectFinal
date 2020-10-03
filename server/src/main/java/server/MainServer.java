package server;

import server.service.autovehicle.CountPartImpl;
import server.service.autovehicle.PartServiceImpl;
import server.service.autovehicle.ServiceOrderServiceImpl;
import server.service.autovehicle.VehicleServiceImpl;
import server.service.client.ClientServiceImpl;
import server.service.client.CompanyServiceImpl;
import server.service.client.PersonServiceImpl;
import server.service.media.PictureServiceImpl;
import server.service.media.SoundServiceImpl;
import server.service.notification.NotificationServiceImpl;
import server.service.user.UserServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {
    public static void main(String[] args) {

        Path x= Paths.get("./server/src/main/resources/blurredImages/nathan-van-egmond-cMUKwEZG2qI-unsplash.jpg");
        try {
            boolean y = Files.deleteIfExists(x);
            System.out.println( y);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Registry registry = LocateRegistry.createRegistry(4545);
            registry.rebind("imageService", new PictureServiceImpl());
            registry.rebind("userService", new UserServiceImpl());
            registry.rebind("partService", new PartServiceImpl());
            registry.rebind("vehicleService", new VehicleServiceImpl());
            registry.rebind("personService", new PersonServiceImpl());
            registry.rebind("companyService", new CompanyServiceImpl());
            registry.rebind("serviceOrder", new ServiceOrderServiceImpl());
            registry.rebind("clientService", new ClientServiceImpl());
            registry.rebind("CountPartService", new CountPartImpl());
            registry.rebind("soundService", new SoundServiceImpl());
            registry.rebind("notificationService", new NotificationServiceImpl());

        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        System.out.println("++++++++++++++++++");
     //   System.out.println(PictureController.getInstance().getPicture());
     //   System.out.println(UserController.getInstance().loginWithUsername("1234", "1234"));


    }
}
