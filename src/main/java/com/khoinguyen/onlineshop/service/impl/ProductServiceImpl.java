package com.khoinguyen.onlineshop.service.impl;

import com.khoinguyen.onlineshop.dto.product.ProductDTOCreate;
import com.khoinguyen.onlineshop.dto.product.ProductDTOResponse;
import com.khoinguyen.onlineshop.entity.Product;
import com.khoinguyen.onlineshop.exception.OnlineShopException;
import com.khoinguyen.onlineshop.mapper.ProductMapper;
import com.khoinguyen.onlineshop.repository.ProductRepository;
import com.khoinguyen.onlineshop.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    @Override
    public List<ProductDTOResponse> getAllProducts() {
        return productRepository
                .findAll().stream()
                .map(ProductMapper::toProductDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTOResponse getProductById(int id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> OnlineShopException.notFound("Product not found"));
        return ProductMapper.toProductDTOResponse(product);
    }

    @Override
    public ProductDTOResponse createProduct(ProductDTOCreate productDTOCreate) {
		Product product = ProductMapper.toProduct(productDTOCreate);
        product = productRepository.save(product);
        return ProductMapper.toProductDTOResponse(product);
    }
}
