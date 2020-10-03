package client.controller.notification;

import lib.dto.notification.Notification;
import lib.dto.user.Category;
import lib.dto.user.UserDto;
import lib.service.NotificationService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class NotificationController implements NotificationService {

    private NotificationService notificationService;

    private NotificationController() {
        try {
            Registry registry = LocateRegistry.getRegistry(4545);
            notificationService = (NotificationService) registry.lookup("notificationService");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    @Override
    public void addUserToNotificationList(UserDto user) {
        try {
            notificationService.addUserToNotificationList(user);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNotificationToWarehouse(Category category, Notification notification){
        try {
            notificationService.sendNotificationToWarehouse(category, notification);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNotificationToUser(String user, Notification notification){
        try {
            notificationService.sendNotificationToUser(user, notification);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Notification> getNotification(UserDto user){
        try {
            return notificationService.getNotification(user);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder{
        public static final NotificationController INSTANCE = new NotificationController();
    }

    public static NotificationController getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
