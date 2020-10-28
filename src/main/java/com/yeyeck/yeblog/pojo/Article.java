package com.yeyeck.yeblog.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Article extends BasePojo {

    private Integer categoryId;    // 类目id

    private String title;    // 文章标题

    private String abstractText;    // 摘要

    private String keywords;    // SEO 关键字

    private String contentMd;    // 文章内容 MD格式

    private String contentHtml;    // 文章内容 HTML格式

    private Integer views;    // 浏览量

    private Integer status;    // 文章状态， 1： 已发布； 0：未发布（草稿）

    private Integer countComments;    // 评论量

    private List<Comment> comments;    // 评论列表

    private List<Label> labels;    // 标签列表

    private List<Integer> labelIds; // 标签 id

    private String category;


}
