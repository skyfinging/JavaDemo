package demo.dao;

import demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface UserDao {

    @Insert("insert into t_user(id,name,age) values (#{id},#{name},#{age})")
    void addUser(User user);

    @Update("update t_user set name=#{name},age=#{age} where id=#{id}")
    void updateUser(User suer);

    @Delete("delete from t_user where id=#{id}")
    void deleteUser(int id);

    @Select("Select id,name,age from t_user where name=#{userName}")
    User findByName(@Param("userName") String userName);

    @Select("select id,name,age from t_user")
    List<User> findAll();
}
