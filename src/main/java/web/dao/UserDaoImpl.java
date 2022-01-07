package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    //
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }
    public void delete(User user) {
        entityManager.remove(user);
    }
    public User findOne(final long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void removeUserById(long id) {
        delete(findOne(id));
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return findOne(id);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
