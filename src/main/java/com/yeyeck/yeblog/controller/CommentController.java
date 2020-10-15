package com.yeyeck.yeblog.controller;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.controller.fo.Batch;
import com.yeyeck.yeblog.controller.fo.CommentFo;
import com.yeyeck.yeblog.pojo.Comment;
import com.yeyeck.yeblog.service.ICommentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {

    private ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public Comment add(@RequestBody @Validated CommentFo commentFo) {
        return commentService.addComment(commentFo);
    }

    @GetMapping("/status/{status}")
    @RequiresAuthentication
    public List<Comment> getComments(@PathVariable("status") Integer status) {
        return commentService.getCommentsByStatus(status);
    }

    @PutMapping("/status/{id}")
    @RequiresAuthentication
    public boolean passChecking(@PathVariable("id") Integer id) {
        return commentService.updateCommentStatus(id, YeConstants.COMMENT_STATUS_PASSED);
    }

    @DeleteMapping("/{id}")
    @RequiresAuthentication
    public boolean deleteById(@PathVariable("id") Integer id) {
        return commentService.deleteById(id);
    }

    @PostMapping("/delete/comments")
    @RequiresRoles("admin")
    public boolean deleteByIds (@RequestBody Batch<Integer> batch) {
        return commentService.deleteCommentByIds(batch.getObjects());
    }
    @PostMapping("/update/status")
    @RequiresRoles("admin")
    public boolean passCheckingByIds (@RequestBody Batch<Integer> batch) {
        return commentService.updateCommentStatus(batch.getObjects(), YeConstants.COMMENT_STATUS_PASSED);
    }
    
}
