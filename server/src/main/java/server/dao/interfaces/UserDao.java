package server.dao.interfaces;

import server.model.user.User;

import java.util.Optional;

public interface UserDao {
    boolean create(User user);

    Optional<User> findByName(String userName);

    Optional<User> findByEmailAdress(String emailAdress);

    int updatePassword(String newPassword, User user);

    boolean addPhoneNumber(User u, String phoneNumber);

    boolean updatePhoneNumber(User u);
}
