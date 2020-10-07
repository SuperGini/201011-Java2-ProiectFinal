package lib.service;

import lib.dto.notification.Notification;
import lib.dto.user.Category;
import lib.dto.user.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NotificationService extends Remote{
    void addUserToNotificationList(UserDto user) throws RemoteException;

    void sendNotificationToWarehouse(Category category, Notification notification) throws RemoteException;

    void sendNotificationToUser(String user, Notification notification) throws RemoteException;

    List<Notification> getNotification(UserDto user)throws RemoteException;
}
