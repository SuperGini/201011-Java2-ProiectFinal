package lib.service;

import lib.dto.user.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {

    void create(UserDto userDto) throws RemoteException;
}
