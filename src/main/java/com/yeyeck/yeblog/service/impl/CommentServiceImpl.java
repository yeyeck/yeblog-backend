package com.yeyeck.yeblog.service.impl;

import com.yeyeck.yeblog.controller.fo.CommentFo;
import com.yeyeck.yeblog.mapper.CommentMapper;
import com.yeyeck.yeblog.pojo.Comment;
import com.yeyeck.yeblog.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    private CommentMapper commentMapper;


    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Comment addComment(CommentFo commentFo) {
        Comment comment = commentFo.toComment();
        if (commentMapper.add(comment) < 1) {
            throw new RuntimeException("数据库错误!");
        }
        return commentMapper.getById(comment.getId());
    }

    @Override
    public List<Comment> getCommentsByStatus(Integer status) {
        return commentMapper.getByStatus(status);
    }

    @Override
    public boolean updateCommentStatus(Integer id, Integer status) {
        if (!(commentMapper.updateStatus(id, status) > 0)) {
            throw new RuntimeException("数据库错误!");
        }
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        commentMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteCommentByIds(List<Integer> ids) {
        ids.forEach(this::deleteById);
        return true;
    }

    @Override
    public boolean updateCommentStatus(List<Integer> ids, Integer status) {
        commentMapper.batchUpdateStatus(ids, status);
        return true;
    }
}
