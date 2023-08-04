package com.khoinguyen.onlineshop.repository;

import com.khoinguyen.onlineshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
