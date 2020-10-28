package com.yeyeck.yeblog.controller.fo;

import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.pojo.Label;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleFo {

    private Integer id;

    @NotEmpty
    private String title;

    @NotNull
    private Integer status;

    @NotEmpty
    private String abstractText;

    @NotEmpty
    private String contentMd;

    @NotEmpty
    private String contentHtml;

    private String keywords;

    private Integer categoryId;

    private List<Label> labels;


    public Article toArticle() {
        Article article = new Article();
        if (this.categoryId != null && this.categoryId > 0) {
            article.setCategoryId(this.categoryId);
        }
        article.setTitle(this.title);
        article.setStatus(this.status);
        article.setAbstractText(this.abstractText);
        article.setKeywords(this.keywords);
        article.setContentMd(this.contentMd);
        article.setContentHtml(this.contentHtml);
        List<Label> labels = this.labels;
        if (labels != null && labels.size() > 0) {
            List<Integer> labelIds = new ArrayList<>();
            labels.stream().forEach(label -> labelIds.add(label.getId()));
            article.setLabelIds(labelIds);
        }

        return article;
    }
}
