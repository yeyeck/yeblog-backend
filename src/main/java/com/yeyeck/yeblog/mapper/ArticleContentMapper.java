package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Article;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleContentMapper {

    @Select("select md from t_article_content where id = #{id}")
    String getMdById(Integer id);

    @Select("select html from t_article_content where id = #{id}")
    String getHtmlById(Integer id);

    @Insert("insert into t_article_content(id, md, html) values (#{id}, #{contentMd}, #{contentHtml})")
    int addArticleContent(Article article);

    @Delete("delete from t_article_content where id = #{id}")
    void deleteByid(Integer id);

    @Update("update t_article_content set md = #{md}, html = #{html} where id = #{id}")
    int updateArticleContent(Integer id, String md, String html);
}
