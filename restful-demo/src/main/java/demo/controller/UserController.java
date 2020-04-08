package demo.controller;

import demo.entity.User;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/addUser", method= RequestMethod.POST)
    public User addUser(User user){
        if(user.getName()==null || user.getName().isEmpty())
            return null;
        return userService.addUser(user);
    }

    @RequestMapping(value="/updateUser")
    public boolean updateUser(User user){
        return userService.updateUser(user);
    }

    /**
     * 给id取另外一个名字“userId”，这时不能直接使用"id"作为这个参数的名字
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser")
    public boolean deleteUser(@RequestParam("userId") int id){
        return userService.deleteUser(id);
    }

    @RequestMapping("/findByName")
    public User findByName(String userName){
        return userService.findByName(userName);
    }

    @RequestMapping("/findAll")
    public  Page<User> findAll(){
        List<User> list = userService.findAll();
        Page<User> page = new PageImpl<>(list);
        return page;
    }
}
