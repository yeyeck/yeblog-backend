package com.yeyeck.yeblog.controller.fo;

import com.yeyeck.yeblog.pojo.Comment;
import com.yeyeck.yeblog.utils.ShiroUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentFo {

    @NotNull
    private Integer articleId;
    @NotNull
    private String author;

    private Integer parentId;

    private Integer replyToId;

    private String replyTo;

    private String email;

    @NotBlank
    private String content;

    public Comment toComment() {
        Comment comment = new Comment();
        if (ShiroUtils.isAuthenticated()) {
            comment.setAdmin(true);
            comment.setStatus(true);
        } else {
            comment.setAdmin(false);
            comment.setStatus(false);
        }
        comment.setParentId(this.parentId);
        comment.setReplyToId(this.replyToId);
        comment.setReplyTo(this.replyTo);
        comment.setArticleId(this.articleId);
        comment.setContent(this.content);
        comment.setAuthor(this.author);
        comment.setEmail(email);
        return comment;
    }
}
