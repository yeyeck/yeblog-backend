package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.service.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select id, title, views, category_id, create_time, update_time from t_article where status = #{status}")
    @Results(value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "countComments", javaType = Integer.class, one = @One(select = "com.yeyeck.yeblog.mapper.CommentMapper.countCommentsByArticleId")),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_id", property = "category", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.CategoryMapper.getNameById"))
    })
    List<Article> getByStatus(Integer status);

    @Select("select id, title, category_id, views, status, abstract_text, update_time from t_article where id = #{id}")
    @Results(id="ArticleMd", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "contentMd", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.ArticleContentMapper.getMdById")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId")),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_id", property = "category", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.CategoryMapper.getNameById"))
    })
    Article getArticleMdById(Integer id);

    @Select("select id, title, category_id, views, status, abstract_text, keywords, update_time from t_article where id = #{id}")
    @Results(id="ArticleHtml", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "contentHtml", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.ArticleContentMapper.getHtmlById")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId")),
            @Result(column = "id", property = "countComments", javaType = Integer.class, one = @One(select = "com.yeyeck.yeblog.mapper.CommentMapper.countCommentsByArticleId")),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_id", property = "category", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.CategoryMapper.getNameById"))
    })
    Article getArticleHtmlById(Integer id);

    @Select("select id, title, category_id, abstract_text, status from t_article where id = #{id}")
    @Results(id="Draft", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "contentMd", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.ArticleContentMapper.getMdById")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId")),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_id", property = "category", javaType = String.class, one = @One(select = "com.yeyeck.yeblog.mapper.CategoryMapper.getNameById"))
    })
    Article getDraftById(Integer id);

    @Insert("insert into t_article(title, status, category_id, abstract_text, keywords, create_time, update_time) " +
            "values(#{title}, #{status}, #{categoryId}, #{abstractText}, #{keywords}, now(), now())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Article article);

    @Update("update t_article set title = #{title}, abstract_text = #{abstractText}, keywords = #{keywords}, category_id = #{categoryId}, update_time = now() " +
            "where id = #{id}")
    int updateArticle(Article article);

    @Select("select id, title, views, abstract_text, update_time, create_time " +
            "from t_article where status = 1 AND ${filterName} = #{filterValue} order by ${orderParam} ${orderType} limit #{start}, #{countPerPage}")
    @Results(id="abstractArticle", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "countComments", javaType = Integer.class, one = @One(select = "com.yeyeck.yeblog.mapper.CommentMapper.countCommentsByArticleId")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId"))
    })
    List<Article> selectArticlesForPage(Page<Article> page);


    @Select("select id, title, views, category_id, create_time, update_time" +
            "from t_article where status = 0 ORDER BY ${orderParam} ${orderType} limit #{start}, #{countPerPage}")
    @Results(value = {
            @Result(column = "id", property = "countComments", javaType = Integer.class, one = @One(select = "com.yeyeck.yeblog.mapper.CommentMapper.countCommentsByArticleId")),
            @Result(column = "id", property = "labels", javaType = List.class, many = @Many(select = "com.yeyeck.yeblog.mapper.LabelMapper.getByArticleId"))
    })
    List<Article> selectDraftsForPage(Page<Article> page);

    @Update("update t_article set status = #{status}, update_time = now() where id = #{id}")
    int updateStatus(Integer id, Integer status);

    @Select("select count(id) from t_article where status = #{statues}")
    int countByStatus(Integer status);

    @Select("select count(id) from t_article WHERE status = 1 and ${filterName} = #{filterValue}")
    int countByFilter(String filterName,  Object filterValue);

    @Delete("delete from t_article WHERE id = #{id}")
    int deleteById(Integer id);
    @Update("update t_article SET views = views + #{views} where id = #{id}")
    int addViews(Integer id, Integer views);

    @Insert("<script>" +
            "insert into t_article_label(article_id, label_id) values " +
            "<foreach collection='labelIds' item='labelId' index='index' separator=','>" +
            "(#{articleId}, #{labelId})" +
            "</foreach>" +
            "</script>")
    int addLabels(Integer articleId, List<Integer> labelIds);

    @Delete("<script>" +
            "deleter from t_article_label where (article_id, label_id) in (" +
            "<foreach collection='labelIds' item='labelId' index='index' separator=','>" +
            "(#{articleId}, #{labelId})" +
            "</foreach>" +
            ")" +
            "</script>")
    int removeLabels(Integer articleId, List<Integer> labelIds);

    @Select("select label_id from t_article_label where article_id = #{articleId}")
    @ResultType(List.class)
    List<Integer> getLabelIds(Integer articleId);

    @Delete("delete from t_article_label where article_id = #{articleId}")
    int removeLabelsByArticleId(Integer articleId);

    @Update("update t_article set category_id = #{categoryId} where id = #{articleId}")
    int updateCategory(Integer articleId, Integer categoryId);

    @Select("select id, title from t_article where status = 1 order by views desc limit 0, 10")
    List<Article> getTop10();

    @Select("select coalesce(sum(views), 0) from t_article")
    int sumViews();

    @Update("update t_article set category_id = NULL where category_id = #{categoryId}")
    void setCategoryToNull(Integer categoryId);


    @Select("select id, title from t_article where id < #{id} and status = 1 order by id limit 0, 1")
    Article previous(Integer id);

    @Select("select id, title from t_article where id > #{id} AND status = 1  order by id limit 0, 1")
    Article next(Integer id);

    @Select("select title from t_article where id = #{id}")
    String getTitleById(@Param("id") Integer id);
}
