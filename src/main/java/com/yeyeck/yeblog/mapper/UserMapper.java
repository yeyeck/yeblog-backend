package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `t_user` WHERE `username` = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO `t_user`(`username`, `nickname`, `password`, `salt`, `role`, `create_time`, `update_time`)" +
            "VALUES(#{username}, #{nickname}, #{password}, #{salt}, #{role}, NOW(), NOW())")
    int add(User user);

    @Update("UPDATE `t_user` SET `nickname` = #{nickname}, `update_time` = NOW() WHERE `id` = #{id}")
    int updateNickname(Integer id, String nickname);

    @Update("UPDATE `t_user` SET `header` = #{header}, `update_time` = NOW() WHERE `id` = #{id}")
    int updateHeader(Integer id, String header);

    @Select("SELECT `nickname` FROM `t_user` WHERE `id` = #{id}")
    String getNicknameById(Integer id);

    @Select("SELECT * FROM `t_user` WHERE `id` = #{userId}")
    User getById(int userId);

    @Select("SELECT `header` FROM `t_user` WHERE `id` = #{id}")
    String getHeaderById(Integer id);

    @Update("UPDATE `t_user` SET `salt` = #{salt}, `password` = #{password}, `update_time` = NOW() WHERE `id` = #{id}")
    int updatePassword(User user);

    @Update("UPDATE `t_user` SET `username`= #{username},`salt` = #{salt}, `password` = #{password}, `update_time` = NOW() WHERE `id` = 1")
    int updateAdmin(User user);
}
