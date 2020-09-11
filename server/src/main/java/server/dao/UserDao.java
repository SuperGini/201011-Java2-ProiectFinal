package server.dao;

import server.model.user.User;

import java.util.Optional;

public interface UserDao {
    void create(User user);

    Optional<User> findByName(String userName);
}
