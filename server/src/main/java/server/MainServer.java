package server;

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

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {
    public static void main(String[] args) {

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
            registry.rebind("soundService", new SoundServiceImpl());
            registry.rebind("notificationService", new NotificationServiceImpl());
            System.out.println("Server -> running");
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Server -> shutdown");
            throw new RuntimeException(e);
        }




    }
}
