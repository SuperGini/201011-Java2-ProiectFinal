package server.service;

import lib.dto.user.UserDto;
import lib.service.UserService;
import server.convert.UserConvertor;
import server.dao.UserDao;
import server.dao.impl.UserDaoImpl;
import server.model.user.User;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    private final  UserDao userDao;

    public UserServiceImpl() throws RemoteException {

        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        this.userDao = new UserDaoImpl(entityManager);
    }

    @Override
    public void create(UserDto userDto) throws RemoteException {
        User user = UserConvertor.convert(userDto);
        userDao.create(user);
    }



}
