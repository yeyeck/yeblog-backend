package com.yeyeck.yeblog.mapper;


import com.yeyeck.yeblog.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select id, name from t_category")
    List<Category> getAll();

    @Delete("delete from t_category where id = #{id}")
    int deleteById(Integer id);

    @Insert("insert into t_category(name, create_time, update_time) values (#{name}, NOW(), NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Category category);

    @Select("select * from t_category where name = #{name}")
    Category getByName(String name);

    @Update("update t_category set name = #{name}, update_time = now() where id = #{id}")
    int update(Integer id, String name);

    @Select("select name from t_category where id = #{id}")
    String getNameById(Integer id);

}
