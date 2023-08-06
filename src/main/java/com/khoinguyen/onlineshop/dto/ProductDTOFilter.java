package com.khoinguyen.onlineshop.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import static com.khoinguyen.onlineshop.util.Constant.DEFAULT_PAGE_SIZE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTOFilter {
    Integer pageIndex = 1;
    Integer pageSize = DEFAULT_PAGE_SIZE;
    Integer categoryId;
    Integer brandId;
    Integer colorId;
    Double priceFrom = 0d;
    Double priceTo = Double.MAX_VALUE;
    String sortByPrice;
    String name = "";

}
