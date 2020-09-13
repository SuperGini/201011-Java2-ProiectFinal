package client.controller;

import lib.dto.user.UserDto;
import lib.service.UserService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UserController implements UserService{

    private UserService userService;

    private UserController() {

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);

            userService = (UserService) registry.lookup("userService");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(UserDto userDto){
        try {
            userService.create(userDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder{
        public static final UserController INSTANCE = new UserController();
    }

    public static UserController getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
