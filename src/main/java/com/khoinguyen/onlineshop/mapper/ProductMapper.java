package com.khoinguyen.onlineshop.mapper;

import com.khoinguyen.onlineshop.dto.product.ProductDTOCreate;
import com.khoinguyen.onlineshop.dto.product.ProductDTOResponse;
import com.khoinguyen.onlineshop.dto.product.ProductDTOUpdate;
import com.khoinguyen.onlineshop.entity.Category;
import com.khoinguyen.onlineshop.entity.Product;

public class ProductMapper {

    public static ProductDTOResponse toProductDTOResponse(Product product) {
        return ProductDTOResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .summary(product.getSummary())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .availability(product.getAvailability())
                .specification(product.getSpecification())
                .category(CategoryMapper.toCategoryDTOResponse(product.getCategory()))
                .build();
    }

    public static Product toProduct(ProductDTOCreate productDTOCreate) {
        return Product.builder()
                .name(productDTOCreate.getName())
                .price(productDTOCreate.getPrice())
                .quantity(productDTOCreate.getQuantity())
                .summary(productDTOCreate.getSummary())
                .description(productDTOCreate.getDescription())
                .imageUrl(productDTOCreate.getImageUrl())
                .availability(productDTOCreate.getAvailability())
                .specification(productDTOCreate.getSpecification())
                .category(Category.builder().id(productDTOCreate.getCategoryId()).build())
                .build();
    }
    public static Product toProduct(ProductDTOUpdate productDTOUpdate, int id) {
        return Product.builder()
                .id(id)
                .name(productDTOUpdate.getName())
                .price(productDTOUpdate.getPrice())
                .quantity(productDTOUpdate.getQuantity())
                .summary(productDTOUpdate.getSummary())
                .description(productDTOUpdate.getDescription())
                .imageUrl(productDTOUpdate.getImageUrl())
                .availability(productDTOUpdate.getAvailability())
                .specification(productDTOUpdate.getSpecification())
                .category(Category.builder().id(productDTOUpdate.getCategoryId()).build())
                .build();
    }
}
