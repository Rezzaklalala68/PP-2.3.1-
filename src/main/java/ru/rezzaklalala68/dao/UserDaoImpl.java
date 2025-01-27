package ru.rezzaklalala68.dao;



import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rezzaklalala68.model.User;


;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from users u", User.class).getResultList();
    }
    public void add(User user) {
        entityManager.persist(user);
    }
    public Optional<User> findUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
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
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        }

    }
}
