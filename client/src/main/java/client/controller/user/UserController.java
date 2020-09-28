package client.controller.user;

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
    public boolean create(UserDto userDto){
        try {
          return  userService.create(userDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean loginWithUsername(String userName, String password){
        try {
          return  userService.loginWithUsername(userName, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto loginWithEmailAdress(String emailAdress, String password){
        try {
           return userService.loginWithEmailAdress(emailAdress, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto loginWithUsername2(String userName, String password) {
        try {
           return userService.loginWithUsername2(userName, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updatePassword(String newPassword, String userName){
        try {
            return userService.updatePassword(newPassword, userName);
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
