package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Label;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LabelMapper {


    @Insert("insert into t_label(name, create_time) values (#{name}, NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Label label);

    @Delete("delete from t_label where id = #{id}")
    int deleteById(Integer id);

    @Select("select * from t_label shere id = #{id}")
    Label getById(Integer id);

    @Select("select t_label.id, t_label.name from t_article_label inner join t_label " +
            "on t_article_label.article_id = #{articleId} and t_article_label.label_id = t_label.id  ")
    List<Label> getByArticleId(Integer articleId);

    @Select({"select * from t_label where name = #{name}", })
    Label getByName(String name);


}
