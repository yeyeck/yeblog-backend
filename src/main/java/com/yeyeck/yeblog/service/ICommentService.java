package com.yeyeck.yeblog.service;

import com.yeyeck.yeblog.controller.fo.CommentFo;
import com.yeyeck.yeblog.pojo.Comment;

import java.util.List;

public interface ICommentService {

    Comment addComment(CommentFo commentFo);

    List<Comment> getCommentsByStatus(Integer status);

    boolean updateCommentStatus(Integer id, Integer status);

    boolean deleteById(Integer id);

    boolean deleteCommentByIds(List<Integer> objects);
    boolean updateCommentStatus(List<Integer> ids, Integer status);
}
