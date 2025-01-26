package ru.rezzaklalala68.dao;

import jakarta.persistence.EntityManager;

import org.springframework.stereotype.Component;
import ru.rezzaklalala68.model.User;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
    public void add(User user) {
        entityManager.persist(user);
    }
    public Optional<User> findUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }
    public void update(User user) {
        entityManager.merge(user);

    }
    public void delete(User user) {
        if (user != null) {
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        }

    }
}
