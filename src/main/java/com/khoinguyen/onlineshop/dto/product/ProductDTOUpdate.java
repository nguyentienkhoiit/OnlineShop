package com.khoinguyen.onlineshop.dto.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTOUpdate {
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
