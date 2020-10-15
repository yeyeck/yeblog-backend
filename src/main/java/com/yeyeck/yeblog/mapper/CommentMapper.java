package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT `id`, `author`, `reply_to_id`, `reply_to`, `content`, `admin`, `status`, `create_time` " +
            "FROM `t_article_comment` " +
            "WHERE `article_id` = #{articleId} AND `parent_id` IS NULL")
    @Results(id="Comment", value ={
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "replies", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.CommentMapper.getByParentId"))
    })
    List<Comment> getByArticleId(Integer articleId);


    @Select("SELECT `id`, `author`, `reply_to_id`, `reply_to`, `content`, `admin`, `status`, `parent_id`, `create_time` " +
            "FROM `t_article_comment` WHERE `parent_id` = #{parentId} ")
    List<Comment> getByParentId(Integer parentId);

    @Select("SELECT COUNT(*) FROM `t_article_comment` WHERE `article_id` = #{articleId}")
    int countCommentsByArticleId(Integer articleId);

    @Insert("INSERT INTO `t_article_comment`(`article_id`, `author`, `parent_id`, `reply_to_id`, `reply_to`, `content`, `admin`, `status`, `email`, `create_time`, `update_time`) " +
            "VALUES (#{articleId}, #{author}, #{parentId}, #{replyToId}, #{replyTo}, #{content}, #{admin}, #{status}, #{email},  NOW(), NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Comment comment);


    @Select("SELECT * FROM `t_article_comment` WHERE `id` = #{id}")
    Comment getById(Integer id);

    @Select("SELECT COUNT(id) FROM `t_article_comment`")
    int countAll();

    @Select("SELECT * FROM `t_article_comment` WHERE `status` = #{status} ORDER BY `create_time`")
    @Results(value ={
            @Result(column = "id", property = "id"),
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "article_id", property = "articleTitle", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.ArticleMapper.getTitleById")),
    })
    List<Comment> getByStatus(Integer status);


    @Update("UPDATE `t_article_comment` SET `status` = #{status} WHERE `id` = #{id} ")
    int updateStatus(Integer id, Integer status);

    @Delete("DELETE FROM `t_article_comment` WHERE `id` = #{id} OR `parent_id` = #{id}")
    int deleteById(Integer id);

    @Update("<script>" +
            "UPDATE `t_article_comment` SET `status` = #{status} WHERE `id` IN (" +
            "<foreach collection='ids' item='id' index='index' separator=','>" +
            "#{id}" +
            "</foreach>" +
            ")" +
            "</script>")
    int batchUpdateStatus(List<Integer> ids, Integer status);
}
