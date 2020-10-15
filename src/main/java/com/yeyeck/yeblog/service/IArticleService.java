package com.yeyeck.yeblog.service;


import com.yeyeck.yeblog.controller.fo.ArticleFo;
import com.yeyeck.yeblog.controller.vo.ArticleVo;
import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.pojo.ArticleStatistics;
import com.yeyeck.yeblog.pojo.Comment;

import java.util.List;

public interface IArticleService {

    Page<Article> getPage(Page<Article> page);

    /**
     * 按 id 检索文章, 内容为MD格式
     * @param id
     * @return Article
     */
    Article getArticleMd(Integer id);

    /**
     * 按 id 检索文章, 内容为HTML格式
     * @param id
     * @return Article
     */
    Article getArticleHtml(Integer id);


    /**
     * 更新文章状态
     * @param id 文章id
     * @param status 最新状态
     * @return boolean
     */
    boolean updateArticleStatus(Integer id, Integer status);

    /**
     * 删除未发布文章（草稿）
     * @param id 文章id
     * @return true if delete successfully
     */
    boolean deleteDraft(Integer id);

    /**
     * 新增文章
     * @param articleFo
     * @return ModelMap
     */
    boolean add(ArticleFo articleFo);

    boolean updateArticle(Integer id, ArticleFo articleFo);

    List<Article> getArticlesByStatus(Integer status);

    void  cache(ArticleFo articleFo);

    List<Comment> getComments(Integer id);

    ArticleFo getCachedArticle();

    boolean clearCache();

    List<Article> getTop10();

    int countArticlesByStatus(Integer status);

    ArticleStatistics getStatistics();

    void hyperLogLogAdd(Integer articleId);

    boolean updateCategory(Integer articleId, Integer categoryId);

    ArticleVo getArticleVo(Integer id);
}
