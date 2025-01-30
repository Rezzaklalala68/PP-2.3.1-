package ru.rezzaklalala68.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rezzaklalala68.dao.UserDao;
import ru.rezzaklalala68.model.User;
import java.util.List;



@Service()
public class UserServiceImpl implements UserService {
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
    public User findUserById(Long id){
        return userDao.findUserById(id);
    }

    @Transactional
    @Override
    public void update(User user) {

        userDao.update(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        userDao.delete(id);
    }


}
