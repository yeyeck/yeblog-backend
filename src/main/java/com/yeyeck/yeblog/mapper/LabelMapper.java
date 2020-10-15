package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Label;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LabelMapper {


    @Insert("INSERT INTO `t_label`(`name`, `create_time`) VALUES (#{name}, NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Label label);

    @Delete("DELETE FROM `t_label` WHERE `id` = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM `t_label` WHERE `id` = #{id}")
    Label getById(Integer id);

    @Select("SELECT t_label.`id`, t_label.`name` FROM `t_article_label` INNER JOIN `t_label` " +
            "ON t_article_label.`article_id` = #{articleId} AND t_article_label.`label_id` = t_label.`id`  ")
    List<Label> getByArticleId(Integer articleId);

    @Select({"SELECT * from `t_label` WHERE `name` = #{name}", })
    Label getByName(String name);


}
