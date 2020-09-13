package server.service;

import lib.dto.user.UserDto;
import server.dao.UserDao;
import server.dao.impl.UserDaoImpl;

import javax.persistence.Persistence;

public class UserServiceImpl {

    private final  UserDao userDao;

    public UserServiceImpl() {

        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        this.userDao = new UserDaoImpl(entityManager);
    }

    public void create(UserDto userDto){


    }



}
