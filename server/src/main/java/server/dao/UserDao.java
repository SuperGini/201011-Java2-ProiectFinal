package server.dao;

import server.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean create(User user);

    Optional<User> findByName(String userName);

    Optional<User> findByEmailAdress(String emailAdress);

    List<User> findByName2(String userName);



    int updatePassword(String newPassword, String userName);

}
