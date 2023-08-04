package com.khoinguyen.onlineshop.service;

import com.khoinguyen.onlineshop.dto.product.ProductDTOCreate;
import com.khoinguyen.onlineshop.dto.product.ProductDTOResponse;
import com.khoinguyen.onlineshop.entity.Product;

import java.util.List;

public interface ProductService {
    public List<ProductDTOResponse> getAllProducts();

    public ProductDTOResponse getProductById(int id);

    public ProductDTOResponse createProduct(ProductDTOCreate productDTOCreate);
}
