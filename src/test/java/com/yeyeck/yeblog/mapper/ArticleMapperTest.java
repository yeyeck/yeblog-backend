package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.service.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Slf4j
class ArticleMapperTest {
    @Autowired
    ArticleMapper articleMapper;

    @Test
    void getArticleById() {
        Article article = articleMapper.getArticleMdById(1);
        log.info(article.toString());
    }

    @Test
    void getDraftById() {
        Article article = articleMapper.getDraftById(1);
        log.info(article.toString());
    }

    @Test
    void add() {
        Article article = new Article();
        article.setTitle("TestAdd");
        article.setAbstractText("## AbstractMd");
        int res = articleMapper.add(article);
        log.info("res: {}, id: {}", res, article.getId());
    }

    @Test
    void updateArticle() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("uuupdate");
        article.setAbstractText("### uuup");
        articleMapper.updateArticle(article);
    }

    @Test
    void selectArticlesForPage() {
        Page<Article> page = new Page<>(1);
        List<Article> list = articleMapper.selectArticlesForPage(page);
        log.info(list.toString());
    }


    @Test
    void selectDraftsForPage() {
        Page<Article> page = new Page<>(1);
        List<Article> list = articleMapper.selectDraftsForPage(page);
        log.info(list.toString());
    }

    @Test
    void updateStatus() {
        int res = articleMapper.updateStatus(1, 1);
        log.info("updateStatus: {}", res);

    }

    @Test
    void countByStatus() {
        log.info("count: {}", articleMapper.countByStatus(1));
    }

    @Test
    void deleteById() {
        log.info("delete res: {}", articleMapper.deleteById(4));
    }

    @Test
    void addLabels() {
    }

    @Test
    void getLabelIds() {
        List<Integer> list = articleMapper.getLabelIds(16);
        log.info(list.toString());
    }

    @Test
    void getTitleById() {
        log.info(articleMapper.getTitleById(1));
    }
}