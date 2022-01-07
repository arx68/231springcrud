package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao dao;
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void saveUser(User user)  {
        dao.saveUser(user);
    }

    @Override
    public void removeUserById(long id)  {
        dao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public User getUser(long id) {
        return dao.getUser(id);
    }

    @Override
    public void updateUser(User updateUser) {
        dao.updateUser(updateUser);
    }
}
