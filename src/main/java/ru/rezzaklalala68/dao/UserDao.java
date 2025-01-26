package ru.rezzaklalala68.dao;

import ru.rezzaklalala68.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getUsers();
    Optional<User> findUserById(Long id);
    void add(User user);
    void update(User user);
    void delete(User user);
}
