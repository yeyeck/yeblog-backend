package com.yeyeck.yeblog.service.impl;

import com.yeyeck.yeblog.controller.fo.Batch;
import com.yeyeck.yeblog.controller.fo.CategoryFo;
import com.yeyeck.yeblog.exception.DataConflictException;
import com.yeyeck.yeblog.mapper.ArticleMapper;
import com.yeyeck.yeblog.mapper.CategoryMapper;
import com.yeyeck.yeblog.pojo.Category;
import com.yeyeck.yeblog.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    private CategoryMapper categoryMapper;

    private ArticleMapper articleMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper, ArticleMapper articleMapper) {
        this.categoryMapper = categoryMapper;
        this.articleMapper = articleMapper;
    }

    @Override
    public Category addCategory(CategoryFo categoryFo) {
        Category category = categoryMapper.getByName(categoryFo.getName());
        if (category != null) {
            throw new DataConflictException("该类目名称已存在！");
        }
        category = categoryFo.toCategory();
        categoryMapper.add(category);
        return category;
    }

    @Override
    public List<Category> getAll() {
        return categoryMapper.getAll();
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        if (categoryMapper.deleteById(id) > 0) {
            articleMapper.setCategoryToNull(id);
        }
        return true;
    }

    @Override
    public boolean updateCategory(Integer id, String name) {
        if (categoryMapper.update(id, name) < 1) {
            throw new RuntimeException("系统开小差啦");
        }
        return true;
    }

    @Override
    public boolean deleteByIds(Batch<Integer> ids) {
        ids.getObjects().forEach(this::deleteById);
        return true;
    }
}
