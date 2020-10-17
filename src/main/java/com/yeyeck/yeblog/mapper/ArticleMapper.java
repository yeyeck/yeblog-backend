package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.service.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("SELECT `id`, `title`, `views`, `category_id`, `create_time`, `update_time` FROM `t_article` WHERE `status` = #{status}")
    @Results(value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "countComments", javaType = Integer.class, one = @One(select = "com.yeyeck.yeblog.mapper.CommentMapper.countCommentsByArticleId")),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_id", property = "category", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.CategoryMapper.getNameById"))
    })
    List<Article> getByStatus(Integer status);

    @Select("SELECT `id`, `title`, `category_id`, `views`, `status`, `abstract_text`, `update_time` FROM `t_article` WHERE `id` = #{id}")
    @Results(id="ArticleMd", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "contentMd", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.ArticleContentMapper.getMdById")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId")),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_id", property = "category", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.CategoryMapper.getNameById"))
    })
    Article getArticleMdById(Integer id);

    @Select("SELECT `id`, `title`, `category_id`, `views`, `status`, `abstract_text`, `update_time` FROM `t_article` WHERE `id` = #{id}")
    @Results(id="ArticleHtml", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "contentHtml", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.ArticleContentMapper.getHtmlById")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId")),
            @Result(column = "id", property = "countComments", javaType = Integer.class, one = @One(select = "com.yeyeck.yeblog.mapper.CommentMapper.countCommentsByArticleId")),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_id", property = "category", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.CategoryMapper.getNameById"))
    })
    Article getArticleHtmlById(Integer id);

    @Select("SELECT `id`, `title`, `category_id`, `abstract_text`, `status` FROM `t_article` WHERE `id` = #{id}")
    @Results(id="Draft", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "contentMd", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.ArticleContentMapper.getMdById")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId")),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_id", property = "category", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.CategoryMapper.getNameById"))
    })
    Article getDraftById(Integer id);

    @Insert("INSERT INTO `t_article`(`title`, `status`, `category_id`, `abstract_text`, `create_time`, `update_time`) " +
            "VALUES(#{title}, #{status}, #{categoryId}, #{abstractText}, NOW(), NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Article article);

    @Update("UPDATE `t_article` SET `title` = #{title}, `abstract_text` = #{abstractText}, `category_id` = #{categoryId}, `update_time` = NOW() WHERE `id` = #{id}")
    int updateArticle(Article article);

    @Select("SELECT `id`, `title`, `views`, `abstract_text`, `update_time`, `create_time` " +
            "FROM `t_article` WHERE `status` = 1 AND ${filterName} = #{filterValue} ORDER BY ${orderParam} ${orderType} LIMIT #{start}, #{countPerPage}")
    @Results(id="abstractArticle", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "countComments", javaType = Integer.class, one = @One(select = "com.yeyeck.yeblog.mapper.CommentMapper.countCommentsByArticleId")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId"))
    })
    List<Article> selectArticlesForPage(Page<Article> page);


    @Select("SELECT `id`, `title`, `views`, `category_id`, `create_time`, `update_time`" +
            "FROM `t_article` WHERE `status` = 0 ORDER BY ${orderParam} ${orderType} LIMIT #{start}, #{countPerPage}")
    @Results(value = {
            @Result(column = "id", property = "countComments", javaType = Integer.class, one = @One(select = "com.yeyeck.yeblog.mapper.CommentMapper.countCommentsByArticleId")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId"))
    })
    List<Article> selectDraftsForPage(Page<Article> page);

    @Update("UPDATE `t_article` SET `status` = #{status}, `update_time` = NOW() WHERE `id` = #{id}")
    int updateStatus(Integer id, Integer status);

    @Select("SELECT COUNT(`id`) FROM `t_article` WHERE `status` = #{statues}")
    int countByStatus(Integer status);

    @Select("SELECT COUNT(`id`) FROM `t_article` WHERE `status` = 1 AND ${filterName} = #{filterValue}")
    int countByFilter(String filterName,  Object filterValue);

    @Delete("DELETE FROM `t_article` WHERE `id` = #{id}")
    int deleteById(Integer id);
    @Update("UPDATE `t_article` SET `views` = `views` + #{views} WHERE `id` = #{id}")
    int addViews(Integer id, Integer views);

    @Insert("<script>" +
            "INSERT INTO `t_article_label`(`article_id`, `label_id`) VALUES " +
            "<foreach collection='labelIds' item='labelId' index='index' separator=','>" +
            "(#{articleId}, #{labelId})" +
            "</foreach>" +
            "</script>")
    int addLabels(Integer articleId, List<Integer> labelIds);

    @Delete("<script>" +
            "DELETE FROM `t_article_label` WHERE (`article_id`, `label_id`) IN (" +
            "<foreach collection='labelIds' item='labelId' index='index' separator=','>" +
            "(#{articleId}, #{labelId})" +
            "</foreach>" +
            ")" +
            "</script>")
    int removeLabels(Integer articleId, List<Integer> labelIds);

    @Select("SELECT `label_id` FROM `t_article_label` WHERE `article_id` = #{articleId}")
    @ResultType(List.class)
    List<Integer> getLabelIds(Integer articleId);

    @Delete("DELETE FROM `t_article_label` WHERE `article_id` = #{articleId}")
    int removeLabelsByArticleId(Integer articleId);

    @Update("UPDATE `t_article` SET `category_id` = #{categoryId} WHERE `id` = #{articleId}")
    int updateCategory(Integer articleId, Integer categoryId);

    @Select("SELECT `id`, `title` FROM `t_article` WHERE `status` = 1 ORDER BY views DESC LIMIT 0, 10")
    List<Article> getTop10();

    @Select("SELECT COALESCE(SUM(`views`), 0) FROM `t_article`")
    int sumViews();

    @Update("UPDATE `t_article` SET `category_id` = NULL WHERE `category_id` = #{categoryId}")
    void setCategoryToNull(Integer categoryId);


    @Select("SELECT `id`, `title` FROM `t_article` WHERE `id` < #{id} AND `status` = 1 ORDER BY `id` LIMIT 0, 1")
    Article previous(Integer id);

    @Select("SELECT `id`, `title` FROM `t_article` WHERE `id` > #{id} AND `status` = 1  ORDER BY `id` LIMIT 0, 1")
    Article next(Integer id);

    @Select("SELECT `title` FROM `t_article` WHERE `id` = #{id}")
    String getTitleById(@Param("id") Integer id);
}
