package com.khoinguyen.onlineshop.repository;

import com.khoinguyen.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
