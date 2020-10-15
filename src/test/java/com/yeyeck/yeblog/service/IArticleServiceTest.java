package com.yeyeck.yeblog.service;

import com.yeyeck.yeblog.controller.fo.ArticleFo;
import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.pojo.Label;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class IArticleServiceTest {

    @Autowired
    IArticleService articleService;

    @Test
    void getPage() {
        Page<Article> page = new Page<>(1);
        articleService.getPage(new Page<Article>(1));
        log.info(page.toString());
    }

    @Test
    void add() {
        ArticleFo articleVo = new ArticleFo();
        articleVo.setTitle("test impl add");
        articleVo.setAbstractText("## test impl add abstract");
        articleVo.setContentMd("## content md");
        List<Label> labels = new ArrayList<>();
        Label l1 = new Label();
        l1.setName("java");
        l1.setId(1);
        labels.add(l1);
        Label l2 = new Label();
        l1.setName("springboot");
        l1.setId(2);
        labels.add(l2);
        Label l3 = new Label();
        l1.setName("mybatis");
        l1.setId(3);
        labels.add(l3);
        articleVo.setLabels(labels);
        articleService.add(articleVo);
    }
}