package demo.mapper;

import demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT * from user where USER_ID=#{userId}")
    User getUser(@Param("userId")  int userId);
}
