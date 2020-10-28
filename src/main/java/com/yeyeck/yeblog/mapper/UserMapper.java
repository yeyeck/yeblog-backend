package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into t_user(username, nickname, password, salt, role, create_time, update_time)" +
            "values(#{username}, #{nickname}, #{password}, #{salt}, #{role}, now(), now())")
    int add(User user);

    @Update("update t_user set nickname = #{nickname}, update_time = now() where id = #{id}")
    int updateNickname(Integer id, String nickname);

    @Update("update t_user SET username= #{username},salt = #{salt}, password = #{password}, update_time = now() where id = 1")
    int updateAdmin(User user);
}
