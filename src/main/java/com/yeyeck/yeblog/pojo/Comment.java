package com.yeyeck.yeblog.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Comment extends BasePojo{
    private Integer articleId;
    private String content;
    private String author;
    private Integer parentId;
    private Integer replyToId;
    private String replyTo;
    private Boolean admin;
    private Boolean status;
    private String email;

    private String articleTitle;
    private List<Comment> replies;
}
