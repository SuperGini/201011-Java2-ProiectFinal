package server.dao;

import server.model.user.User;

import java.util.Optional;

public interface UderDao {
    void create(User user);

    Optional<User> findByName(String userName);
}
