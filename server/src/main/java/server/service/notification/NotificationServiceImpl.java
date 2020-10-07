package server.service.notification;

import lib.dto.notification.Notification;
import lib.dto.user.Category;
import lib.dto.user.UserDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class NotificationServiceImpl extends UnicastRemoteObject implements lib.service.NotificationService {

    private final Map<UserDto, Queue<Notification>> notifications = new ConcurrentHashMap<>();

    public NotificationServiceImpl() throws RemoteException {
    }

    @Override
    public void addUserToNotificationList(UserDto user) throws RemoteException{
        notifications.put(user, new LinkedBlockingQueue<>());
    }

    @Override
    public void sendNotificationToWarehouse(Category category, Notification notification) throws RemoteException{

        notifications.entrySet().stream()
                .filter( s-> s.getKey().getCategory().equals(Category.WAREHOUSE))
                .forEach(s ->s.getValue().add(notification));
    }

    @Override
    public void sendNotificationToUser(String user, Notification notification) throws RemoteException{
        notifications.entrySet().stream()
                .filter(s ->s.getKey().getUserId().getUserName().equals(user))
                .forEach(s ->s.getValue().add(notification));
    }

    @Override
    public List<Notification> getNotification(UserDto user)throws RemoteException{

            Queue<Notification> queue = notifications.get(user);
            notifications.put(user, new LinkedBlockingQueue<>());
            return new ArrayList<>(queue);
    }







}
