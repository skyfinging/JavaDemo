package demo.service;

import demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    User addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    User findByName(String userName);

    List<User> findAll();
}
