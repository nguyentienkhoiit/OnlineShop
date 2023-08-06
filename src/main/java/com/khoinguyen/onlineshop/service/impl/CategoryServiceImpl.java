package com.khoinguyen.onlineshop.service.impl;

import com.khoinguyen.onlineshop.dto.category.CategoryDTOCreate;
import com.khoinguyen.onlineshop.dto.category.CategoryDTOResponse;
import com.khoinguyen.onlineshop.dto.category.CategoryDTOUpdate;
import com.khoinguyen.onlineshop.entity.Category;
import com.khoinguyen.onlineshop.exception.OnlineShopException;
import com.khoinguyen.onlineshop.mapper.CategoryMapper;
import com.khoinguyen.onlineshop.repository.CategoryRepository;
import com.khoinguyen.onlineshop.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    @Override
    public CategoryDTOResponse createCategory(CategoryDTOCreate categoryDTOCreate) {
        Category category = CategoryMapper.toCategory(categoryDTOCreate);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTOResponse(category);
    }

    @Override
    public List<CategoryDTOResponse> getAllCategories() {
        return categoryRepository
                .findAll().stream()
                .map(CategoryMapper::toCategoryDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTOResponse getCategoryById(int id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> OnlineShopException.notFound("Category doest not exist"));
        return CategoryMapper.toCategoryDTOResponse(category);
    }

    @Override
    public CategoryDTOResponse updateCategory(CategoryDTOUpdate categoryDTOUpdate, int id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> OnlineShopException.notFound("Category doest not exist"));
        category = CategoryMapper.toCategory(categoryDTOUpdate, id);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTOResponse(category);
    }

    @Override
    public CategoryDTOResponse deleteCategory(int id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> OnlineShopException.notFound("Category doest not exist"));
        categoryRepository.deleteById(id);
        return CategoryMapper.toCategoryDTOResponse(category);
    }
}
