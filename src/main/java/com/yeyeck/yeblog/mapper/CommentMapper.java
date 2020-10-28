package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select id, author, reply_to_id, reply_to, content, admin, status, create_time " +
            "from t_article_comment " +
            "where article_id = #{articleId} and parent_id is NULL")
    @Results(id="Comment", value ={
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "replies", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.CommentMapper.getByParentId"))
    })
    List<Comment> getByArticleId(Integer articleId);


    @Select("select id, author, reply_to_id, reply_to, content, admin, status, parent_id, create_time " +
            "from t_article_comment where parent_id = #{parentId} ")
    List<Comment> getByParentId(Integer parentId);

    @Select("select count(*) from t_article_comment where article_id = #{articleId}")
    int countCommentsByArticleId(Integer articleId);

    @Insert("insert into t_article_comment(article_id, author, parent_id, reply_to_id, reply_to, content, admin, status, email, create_time, update_time) " +
            "values (#{articleId}, #{author}, #{parentId}, #{replyToId}, #{replyTo}, #{content}, #{admin}, #{status}, #{email},  NOW(), NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Comment comment);


    @Select("select * from t_article_comment where id = #{id}")
    Comment getById(Integer id);

    @Select("select count(id) from t_article_comment")
    int countAll();

    @Select("select * from t_article_comment where status = #{status} order by create_time")
    @Results(value ={
            @Result(column = "id", property = "id"),
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "article_id", property = "articleTitle", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.ArticleMapper.getTitleById")),
    })
    List<Comment> getByStatus(Integer status);


    @Update("update t_article_comment set status = #{status} where id = #{id} ")
    int updateStatus(Integer id, Integer status);

    @Delete("delete from t_article_comment where id = #{id} or parent_id = #{id}")
    int deleteById(Integer id);

    @Update("<script>" +
            "update t_article_comment set status = #{status} shere id IN (" +
            "<foreach collection='ids' item='id' index='index' separator=','>" +
            "#{id}" +
            "</foreach>" +
            ")" +
            "</script>")
    int batchUpdateStatus(List<Integer> ids, Integer status);
}
