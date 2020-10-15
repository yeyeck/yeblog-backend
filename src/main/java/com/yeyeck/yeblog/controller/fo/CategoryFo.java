package com.yeyeck.yeblog.controller.fo;

import com.yeyeck.yeblog.pojo.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryFo {
    @NotBlank
    String name;

    public Category toCategory() {
        Category category = new Category();
        category.setName(this.name);
        return category;
    }

}
