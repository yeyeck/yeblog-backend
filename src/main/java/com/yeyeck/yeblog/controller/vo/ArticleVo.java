package com.yeyeck.yeblog.controller.vo;

import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.pojo.Comment;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {

    private Article article;

    private Article previous;

    private Article next;

    private List<Comment> comments;
    
}
