package com.khoinguyen.onlineshop.service;

import com.khoinguyen.onlineshop.dto.ProductDTOFilter;
import com.khoinguyen.onlineshop.dto.product.PagingDTOResponse;
import com.khoinguyen.onlineshop.dto.product.ProductDTOCreate;
import com.khoinguyen.onlineshop.dto.product.ProductDTOResponse;
import com.khoinguyen.onlineshop.dto.product.ProductDTOUpdate;
import com.khoinguyen.onlineshop.entity.Product;

import java.util.List;

public interface ProductService {
    public PagingDTOResponse searchProduct(ProductDTOFilter productDTOFilter);

    public ProductDTOResponse getProductById(int id);

    public ProductDTOResponse createProduct(ProductDTOCreate productDTOCreate);

    public ProductDTOResponse updateProduct(ProductDTOUpdate productDTOUpdate, int id);

    public ProductDTOResponse deleteProduct(int id);
}
