package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class CommentMapperTest {
    @Autowired
    CommentMapper commentMapper;
    @Test
    void getByArticleId() {
        List<Comment> comments = commentMapper.getByArticleId(1);
        log.info(comments.toString());
    }

    @Test
    void countCommentsByArticleId() {
        Integer count = commentMapper.countCommentsByArticleId(1);
        log.info("count: {}",  count);
    }

    @Test
    void getByStatus() {
        log.info(commentMapper.getByStatus(0).toString());
    }
}