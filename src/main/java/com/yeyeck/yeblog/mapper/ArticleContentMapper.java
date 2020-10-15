package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Article;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleContentMapper {

    @Select("SELECT `md` FROM `t_article_content` WHERE `id` = #{id}")
    String getMdById(Integer id);

    @Select("SELECT `html` FROM `t_article_content` WHERE `id` = #{id}")
    String getHtmlById(Integer id);

    @Insert("INSERT INTO `t_article_content`(`id`, `md`, `html`) VALUES (#{id}, #{contentMd}, #{contentHtml})")
    int addArticleContent(Article article);

    @Delete("DELETE FROM `t_article_content` WHERE `id` = #{id}")
    void deleteByid(Integer id);

    @Update("UPDATE `t_article_content` SET `md` = #{md}, `html` = #{html} WHERE `id` = #{id}")
    int updateArticleContent(Integer id, String md, String html);
}
