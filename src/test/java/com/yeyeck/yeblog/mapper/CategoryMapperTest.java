package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CategoryMapperTest {

    @Autowired
    CategoryMapper categoryMapper;

    @Test
    void add() {
        Category category1 = new Category();
        category1.setName("HTML");
        categoryMapper.add(category1);

        Category category2 = new Category();
        category2.setName("YeBlog");
        categoryMapper.add(category2);

    }

    @Test
    void getAll() {
        List<Category> categories = categoryMapper.getAll();
        log.info(categories.toString());
    }

    @Test
    void addToCategory() {
    }

    @Test
    void countArticlesByCategoryId() {

    }

    @Test
    void getArticleByPage() {
    }
    @Test
    void deleteById() {
    }
}