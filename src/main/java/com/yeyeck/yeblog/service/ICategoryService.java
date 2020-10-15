package com.yeyeck.yeblog.service;

import com.yeyeck.yeblog.controller.fo.Batch;
import com.yeyeck.yeblog.controller.fo.CategoryFo;
import com.yeyeck.yeblog.pojo.Category;

import java.util.List;

public interface ICategoryService {

    Category addCategory(CategoryFo categoryFo);

    List<Category> getAll();

    boolean deleteById(Integer id);

    boolean updateCategory(Integer id, String name);


    boolean deleteByIds(Batch<Integer> ids);
}
