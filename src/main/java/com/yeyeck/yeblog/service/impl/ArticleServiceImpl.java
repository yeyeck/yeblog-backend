package com.yeyeck.yeblog.service.impl;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.controller.fo.ArticleFo;
import com.yeyeck.yeblog.controller.vo.ArticleVo;
import com.yeyeck.yeblog.dao.IRedisDao;
import com.yeyeck.yeblog.exception.StatusException;
import com.yeyeck.yeblog.mapper.ArticleContentMapper;
import com.yeyeck.yeblog.mapper.ArticleMapper;
import com.yeyeck.yeblog.exception.NoSuchDataException;
import com.yeyeck.yeblog.mapper.CommentMapper;
import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.pojo.ArticleStatistics;
import com.yeyeck.yeblog.pojo.Comment;
import com.yeyeck.yeblog.service.IArticleService;
import com.yeyeck.yeblog.service.Page;
import com.yeyeck.yeblog.utils.KeyUtils;
import com.yeyeck.yeblog.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ArticleServiceImpl implements IArticleService {

    private ArticleMapper articleMapper;

    private ArticleContentMapper articleContentMapper;

    private CommentMapper commentMapper;

    private IRedisDao redisDao;


    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleContentMapper articleContentMapper, IRedisDao redisDao,
                              CommentMapper commentMapper) {
        this.articleMapper = articleMapper;
        this.articleContentMapper = articleContentMapper;
        this.redisDao = redisDao;
        this.commentMapper = commentMapper;
    }

    @Override
    public Page<Article> getPage(Page<Article> page) {
        int totalCount = articleMapper.countByFilter(page.getFilterName(), page.getFilterValue());
        page.setTotalCount(totalCount);
        List<Article> articles = articleMapper.selectArticlesForPage(page);
        for (Article article : articles) {
            Integer views = article.getViews();
            Integer todayViews = getArticleViewsToday(article.getId());
            article.setViews(views + todayViews);
        }
        page.setData(articles);
        return page;
    }

    @Override
    public Article getArticleMd(Integer id) {

        Article article = articleMapper.getArticleMdById(id);
        if (article == null) {
           throw new NoSuchDataException("Article does not exist");
        }
        return  article;
    }

    @Override
    public Article getArticleHtml(Integer id) {
        Article article = articleMapper.getArticleHtmlById(id);
        if (article == null) {
            throw new NoSuchDataException("文章不存在！");
        }
        Integer views = article.getViews();
        Integer todayViews = getArticleViewsToday(id);
        article.setViews(views + todayViews);
        return  article;
    }

    @Override
    public boolean updateArticleStatus(Integer id, Integer status) {
        checkResource(id);
        articleMapper.updateStatus(id, status);
        return true;
    }

    @Override
    public boolean deleteDraft(Integer id) {
        Article article = checkResource(id);
        if (article.getStatus() != YeConstants.ARTICLE_STATUS_DRAFT) {
            throw  new StatusException("该文章已经发布，请先设置为草稿后进行删除操作");
        }
        articleMapper.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean add(ArticleFo articleFo) {
        Article article = articleFo.toArticle();
        articleMapper.add(article);    // 插入文章基本信息
        articleContentMapper.addArticleContent(article);    // 插入文章内容
        List<Integer> labelIds = article.getLabelIds();
        if (labelIds != null && labelIds.size() > 0) {
            articleMapper.addLabels(article.getId(), labelIds);    // 插入标签
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateArticle(Integer id, ArticleFo articleFo) {
        checkResource(id);
        Article article = articleFo.toArticle();
        // 1. 更新基础信息
        article.setId(id);
        articleMapper.updateArticle(article);
        // 2. 更新文章内容
        articleContentMapper.updateArticleContent(article.getId(), article.getContentMd(), article.getContentHtml());
        // 3. 更新 label
        List<Integer> labelIds = article.getLabelIds();
        List<Integer> idsExist = articleMapper.getLabelIds(article.getId());
        if (labelIds != null && idsExist != null) {
            List<Integer> tmp = new ArrayList<>(idsExist);
            idsExist.removeAll(labelIds);
            labelIds.removeAll(tmp);
        }
        if (labelIds != null && labelIds.size() > 0)
            articleMapper.addLabels(id, labelIds);
        if (idsExist != null && idsExist.size() > 0)
            articleMapper.removeLabels(id, idsExist);
        return true;
    }

    @Override
    public List<Article> getArticlesByStatus(Integer status) {
        List<Article> articles = articleMapper.getByStatus(status);
        return articles;
    }

    @Override
    public void cache(ArticleFo articleFo) {
        redisDao.add(KeyUtils.keyOfArticleInEditing(), articleFo);
    }

    @Override
    public ArticleFo getCachedArticle() {
        return redisDao.get(KeyUtils.keyOfArticleInEditing());
    }

    @Override
    public List<Comment> getComments(Integer id) {
        return commentMapper.getByArticleId(id);
    }

    @Override
    public boolean clearCache() {
        return redisDao.delete(KeyUtils.keyOfArticleInEditing());
    }

    @Override
    public List<Article> getTop10() {
        return articleMapper.getTop10();
    }

    @Override
    public int countArticlesByStatus(Integer status) {
        return articleMapper.countByStatus(status);
    }

    @Override
    public ArticleStatistics getStatistics() {
        ArticleStatistics statistics = new ArticleStatistics();
        int totalArticles = articleMapper.countByStatus(YeConstants.ARTICLE_STATUS_PUBLISHED);
        statistics.setTotalArticles(totalArticles);
        int totalViews = articleMapper.sumViews();
        statistics.setTotalViews(totalViews);
        int totalComments = commentMapper.countAll();
        statistics.setTotalComments(totalComments);
        return statistics;
    }

    @Override
    public void hyperLogLogAdd(Integer articleId) {
        redisDao.hyperLogLogAdd(KeyUtils.getKeyOfArticleViews(articleId), RequestUtils.getRequestIp());
    }

    @Override
    public boolean updateCategory(Integer articleId, Integer categoryId) {
        articleMapper.updateCategory(articleId, categoryId);
        return true;
    }

    @Override
    public ArticleVo getArticleVo(Integer id) {
        Article article = this.getArticleHtml(id);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setArticle(article);
        articleVo.setComments(this.getComments(id));
        articleVo.setPrevious(articleMapper.previous(id));
        articleVo.setNext(articleMapper.next(id));
        return articleVo;
    }

    private Article checkResource(Integer articleId) {
        Article article = articleMapper.getDraftById(articleId);
        if (article == null) {
            throw new NoSuchDataException("Article does not exist.");
        }
        return article;
    }

    private Integer getArticleViewsToday(Integer articleId) {
        String key = KeyUtils.getKeyOfArticleViews(articleId);
        if (!redisDao.hasKey(key))
            return 0;
        Integer viewsToday = redisDao.hyperLogLogSize(key).intValue();
        return viewsToday;
    }


}
