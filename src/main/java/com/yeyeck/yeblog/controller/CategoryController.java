package com.yeyeck.yeblog.controller;


import com.yeyeck.yeblog.controller.fo.Batch;
import com.yeyeck.yeblog.controller.fo.CategoryFo;
import com.yeyeck.yeblog.pojo.Category;
import com.yeyeck.yeblog.service.ICategoryService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {

    private ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    @RequiresAuthentication
    public Category add(@Validated @RequestBody CategoryFo categoryFo) {
        return categoryService.addCategory(categoryFo);
    }

    @PutMapping("/{id}")
    @RequiresAuthentication
    public boolean updateCategory(@PathVariable("id") Integer id, @Validated @RequestBody CategoryFo categoryFo) {
        return categoryService.updateCategory(id, categoryFo.getName());
    }

    @GetMapping("")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @DeleteMapping("/{id}")
    @RequiresAuthentication
    public boolean deleteCategory(@PathVariable("id") Integer id) {
        return categoryService.deleteById(id);
    }

    @PostMapping("/delete")
    @RequiresAuthentication
    public boolean deleteByIds (@RequestBody Batch<Integer> ids) {
        return categoryService.deleteByIds(ids);
    }
}
