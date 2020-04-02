package demo.controller;

import demo.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping("/")
    public String test1(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String listSql = "user.listUser";
        List<User> userList = sqlSession.selectList(listSql);
        for(User user : userList){
            System.out.println("用户ID:"+user.getUserId());
            System.out.println("用户姓名:"+user.getUserName());
            System.out.println("用户密码:"+user.getPassword());
            System.out.println();
        }
        sqlSession.close();
        return "success";
    }
}
