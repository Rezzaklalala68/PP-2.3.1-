package ru.rezzaklalala68.dao;


import org.springframework.stereotype.Repository;
import ru.rezzaklalala68.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
        entityManager.merge(user);
    }
    public void delete(Long id) {
        entityManager.remove(findUserById(id));
    }
}
