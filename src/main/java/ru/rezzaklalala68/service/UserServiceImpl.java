package ru.rezzaklalala68.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.rezzaklalala68.dao.UserDao;
import ru.rezzaklalala68.model.User;
import java.util.List;
import java.util.Optional;


@Service()
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {return userDao.getUsers();
    }
    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }
    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id){return userDao.findUserById(id);}
    @Transactional
    @Override
    public void update(User user) { userDao.update(user); }

    @Transactional
    @Override
    public void delete(User user) {
        if (user == null) {
            logger.error("Attempted to delete a null user");
            throw new IllegalArgumentException("User cannot be null");
        }
        logger.info("Deleting user with ID: {}", user.getId());
        userDao.delete(user);
    }


}
