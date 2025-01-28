package ru.rezzaklalala68.dao;

import org.springframework.stereotype.Repository;
import ru.rezzaklalala68.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User ", User.class).getResultList();
    }
    public void add(User user) {
        entityManager.persist(user);
    }
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }
    public void update(User user) {
        User userToUpdate = entityManager.find(User.class, user.getId());
        if(userToUpdate != null) {
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setAge(user.getAge());
            entityManager.merge(userToUpdate);
        }


    }
    public void delete(User user) {
        if (user != null) {
            entityManager.remove(user);
        }

    }
}
