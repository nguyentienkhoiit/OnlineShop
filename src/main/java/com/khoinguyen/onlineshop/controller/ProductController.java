package com.khoinguyen.onlineshop.controller;

import com.khoinguyen.onlineshop.dto.product.ProductDTOCreate;
import com.khoinguyen.onlineshop.dto.product.ProductDTOResponse;
import com.khoinguyen.onlineshop.entity.Product;
import com.khoinguyen.onlineshop.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.khoinguyen.onlineshop.util.Constant.API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_VERSION + "/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @GetMapping()
    public List<ProductDTOResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTOResponse getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping()
    public ProductDTOResponse createProduct(@RequestBody ProductDTOCreate productDTOCreate) {
        return productService.createProduct(productDTOCreate);
    }
}
