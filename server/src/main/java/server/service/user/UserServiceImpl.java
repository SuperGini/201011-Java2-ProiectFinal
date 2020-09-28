package server.service.user;

import lib.dto.user.UserDto;
import lib.service.UserService;
import server.convert.user.UserConvertor;
import server.dao.UserDao;
import server.dao.impl.user.UserDaoImpl;
import server.model.user.User;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    private final  UserDao userDao;

    public UserServiceImpl() throws RemoteException {

        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        this.userDao = new UserDaoImpl(entityManager);
    }

    @Override
    public boolean create(UserDto userDto) throws RemoteException {
        User user = UserConvertor.convert(userDto);
       return userDao.create(user);
    }


    //todo: de sters la final ca nu o mai folosesc
    @Override
    public boolean loginWithUsername(String userName, String password) throws RemoteException {
        Optional<User> user = userDao.findByName(userName);

        return user.filter(u -> u.getPassword().equals(password))
                .isPresent();

    }
    //todo: de sters la final ca nu o mai folosesc
    @Override
    public UserDto loginWithEmailAdress(String emailAdress, String password) throws RemoteException {
        Optional<User> user = userDao.findByEmailAdress(emailAdress);

        return user.filter(u -> u.getPassword().equals(password))
                .map(UserConvertor::convert)
                .orElseThrow( NoSuchElementException::new);
    }

    @Override
    public UserDto loginWithUsername2(String userName, String password) throws RemoteException {
        Optional<User> user = userDao.findByName(userName);


        return user.filter(u -> u.getPassword().equals(password))
                .map(UserConvertor::convert)
                .orElseThrow( NoSuchElementException::new);

    }

    @Override
    public int updatePassword(String newPassword, String userName) throws RemoteException{
       return userDao.updatePassword(newPassword, userName);

    }



}
