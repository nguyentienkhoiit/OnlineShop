package com.khoinguyen.onlineshop.mapper;

import com.khoinguyen.onlineshop.dto.category.CategoryDTOCreate;
import com.khoinguyen.onlineshop.dto.category.CategoryDTOResponse;
import com.khoinguyen.onlineshop.dto.category.CategoryDTOUpdate;
import com.khoinguyen.onlineshop.entity.Category;

public class CategoryMapper {
    public static Category toCategory(CategoryDTOCreate categoryDTOCreate) {
        return Category.builder()
                .categoryName(categoryDTOCreate.getCategoryName())
                .build();
    }

    public static Category toCategory(CategoryDTOUpdate categoryDTOUpdate, int id) {
        return Category.builder()
                .id(id)
                .categoryName(categoryDTOUpdate.getCategoryName())
                .build();
    }

    public static CategoryDTOResponse toCategoryDTOResponse(Category category) {
        return CategoryDTOResponse.builder()
                .categoryName(category.getCategoryName())
                .id(category.getId())
                .build();
    }
}
