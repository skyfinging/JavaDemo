package demo.service;

import demo.dao.UserDao;
import demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User addUser(User user) {
          userDao.addUser(user);
          return user;
    }

    @Override
    public boolean updateUser(User user) {
        userDao.updateUser(user);
        return true;
    }

    @Override
    public boolean deleteUser(int id) {
        userDao.deleteUser(id);
        return true;
    }

    @Override
    public User findByName(String userName) {
        return userDao.findByName(userName);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
