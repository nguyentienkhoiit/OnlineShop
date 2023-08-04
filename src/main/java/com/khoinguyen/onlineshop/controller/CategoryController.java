package com.khoinguyen.onlineshop.controller;

import com.khoinguyen.onlineshop.dto.category.CategoryDTOCreate;
import com.khoinguyen.onlineshop.dto.category.CategoryDTOResponse;
import com.khoinguyen.onlineshop.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.khoinguyen.onlineshop.util.Constant.API_VERSION;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(API_VERSION+"/categories")
public class CategoryController {
    CategoryService categoryService;

    @PostMapping("")
    public CategoryDTOResponse createCategory(@RequestBody CategoryDTOCreate categoryDTOCreate) {
        return categoryService.createCategory(categoryDTOCreate);
    }

    @GetMapping("")
    public List<CategoryDTOResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTOResponse getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }
}
