package com.yeyeck.yeblog.mapper;


import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.pojo.Category;
import com.yeyeck.yeblog.service.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT `id`, `name` FROM `t_category`")
    List<Category> getAll();

    @Delete("DELETE FROM `t_category` WHERE `id` = #{id}")
    int deleteById(Integer id);

    @Insert("INSERT INTO `t_category`(`name`, `create_time`, `update_time`) VALUES (#{name}, NOW(), NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Category category);

    @Select("SELECT * FROM `t_category` WHERE `name` = #{name}")
    Category getByName(String name);

    @Update("UPDATE `t_category` SET `name` = #{name}, `update_time` = NOW() WHERE `id` = #{id}")
    int update(Integer id, String name);

    @Select("SELECT `name` FROM `t_category` WHERE `id` = #{id}")
    String getNameById(Integer id);

}
