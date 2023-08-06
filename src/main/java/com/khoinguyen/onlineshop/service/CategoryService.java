package com.khoinguyen.onlineshop.service;

import com.khoinguyen.onlineshop.dto.category.CategoryDTOCreate;
import com.khoinguyen.onlineshop.dto.category.CategoryDTOResponse;
import com.khoinguyen.onlineshop.dto.category.CategoryDTOUpdate;

import java.util.List;

public interface CategoryService {
    public CategoryDTOResponse createCategory(CategoryDTOCreate categoryDTOCreate);

    public List<CategoryDTOResponse> getAllCategories();

    public CategoryDTOResponse getCategoryById(int id);

    public CategoryDTOResponse updateCategory(CategoryDTOUpdate categoryDTOUpdate, int id);

    public CategoryDTOResponse deleteCategory(int id);
}
