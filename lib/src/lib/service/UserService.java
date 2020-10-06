package lib.service;

import lib.dto.user.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {

    boolean create(UserDto userDto) throws RemoteException;


    UserDto loginWithEmailAdress(String emailAdress, String password) throws RemoteException;


    UserDto loginWithUsername2(String userName, String password) throws RemoteException;

    int updatePassword(String newPassword, UserDto userDto) throws RemoteException;


    boolean addPhoneNumber(UserDto userDto, String phoneNumber) throws RemoteException;

    boolean updatePhoneNumber(UserDto userDto) throws RemoteException;
}
