package com.yeyeck.yeblog.controller;

import com.yeyeck.yeblog.controller.fo.ArticleFo;
import com.yeyeck.yeblog.controller.fo.StatusFo;
import com.yeyeck.yeblog.controller.vo.ArticleVo;
import com.yeyeck.yeblog.dao.IRedisDao;
import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.pojo.Comment;
import com.yeyeck.yeblog.service.IArticleService;
import com.yeyeck.yeblog.service.Page;
import com.yeyeck.yeblog.utils.KeyUtils;
import com.yeyeck.yeblog.utils.RequestUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private static final String ARTICLE_FILTER_CATEGORY = "category_id";

    private IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/page/{page}")
    public Page<Article> getArticlePage(@PathVariable("page") Integer pageNum) {

        return articleService.getPage(new Page<Article>(pageNum));
    }

    @GetMapping("/category/{categoryId}/page/{page}")
    public Page<Article> getArticlePage(@PathVariable("categoryId") Integer categoryId, @PathVariable("page") Integer pageNum) {
        Page<Article> page = new Page<>(pageNum);
        page.setFilterName(ARTICLE_FILTER_CATEGORY);
        page.setFilterValue(categoryId);
        return articleService.getPage(page);
    }

    @GetMapping("/top10")
    public List<Article> getTop10() {
        return articleService.getTop10();
    }

    @GetMapping("/list/{status}")
    @RequiresAuthentication
    public List<Article> getArticles(@PathVariable Integer status) {
        return articleService.getArticlesByStatus(status);
    }

    @PostMapping("")
    @RequiresRoles(value={"author", "admin"}, logical = Logical.OR)
    public boolean add(@Validated @RequestBody ArticleFo articleFo) {
        return articleService.add(articleFo);
    }

    @GetMapping("/md/{id}")
    @RequiresRoles(value={"author", "admin"}, logical = Logical.OR)
    public Article getMd(@PathVariable("id") Integer id) {
        return articleService.getArticleMd(id);
    }

    @GetMapping("/html/{id}")
    public ArticleVo getHtml(@PathVariable("id") Integer id) {
        ArticleVo articleVo = articleService.getArticleVo(id);
        // 统计阅读量
        articleService.hyperLogLogAdd(id);
        return articleVo;
    }

    @DeleteMapping("/{id}")
    @RequiresRoles(value={"author", "admin"}, logical = Logical.OR)
    public boolean delete(@PathVariable("id") Integer id) {
        return articleService.deleteDraft(id);
    }

    @PutMapping("/status/{id}")
    @RequiresRoles(value={"author", "admin"}, logical = Logical.OR)
    public boolean updateStatus(@PathVariable("id") Integer id, @Validated @RequestBody StatusFo statusFo) {
        return articleService.updateArticleStatus(id, statusFo.getStatus());
    }

    @PutMapping("/{id}")
    @RequiresRoles(value={"author", "admin"}, logical = Logical.OR)
    public boolean update(@PathVariable("id") Integer id, @Validated @RequestBody ArticleFo articleFo) {
        return articleService.updateArticle(id, articleFo);
    }

    @PutMapping("/cache")
    @RequiresRoles(value={"author", "admin"}, logical = Logical.OR)
    public boolean cache(@RequestBody ArticleFo articleFo) {
        articleService.cache(articleFo);
        return true;
    }

    @GetMapping("/cache")
    @RequiresRoles(value={"author", "admin"}, logical = Logical.OR)
    public ArticleFo getCache() {
        return articleService.getCachedArticle();
    }

    @DeleteMapping("/cache")
    @RequiresRoles(value={"author", "admin"}, logical = Logical.OR)
    public boolean clearCache() {
        return articleService.clearCache();
    }

    @GetMapping("/comment/{id}")
    public List<Comment> getComments(@PathVariable("id") Integer id) {
        return articleService.getComments(id);
    }

    @PatchMapping("/{id}/category/{categoryId}")
    public boolean updateCategory(@PathVariable("id") Integer articleId, @PathVariable("categoryId") Integer categoryId) {
        return articleService.updateCategory(articleId, categoryId);
    }
}
