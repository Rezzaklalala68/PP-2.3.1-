package ru.rezzaklalala68.service;

import ru.rezzaklalala68.model.User;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    User findUserById(Long id);
    void add(User user);
    void update(User user);
    void delete(Long id);
}
