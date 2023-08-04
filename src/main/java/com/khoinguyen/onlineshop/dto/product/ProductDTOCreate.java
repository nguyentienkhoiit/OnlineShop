package com.khoinguyen.onlineshop.dto.product;

import com.khoinguyen.onlineshop.dto.category.CategoryDTOResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ProductDTOCreate {
    String name;
    double price;
    int quantity;
    String summary;
    String description;
    String imageUrl;
    String availability;
    String specification;
    int categoryId;
}
