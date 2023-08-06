package com.khoinguyen.onlineshop.controller;

import com.khoinguyen.onlineshop.dto.ProductDTOFilter;
import com.khoinguyen.onlineshop.dto.product.PagingDTOResponse;
import com.khoinguyen.onlineshop.dto.product.ProductDTOCreate;
import com.khoinguyen.onlineshop.dto.product.ProductDTOResponse;
import com.khoinguyen.onlineshop.dto.product.ProductDTOUpdate;
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

    @GetMapping("/search")
    public PagingDTOResponse searchProduct(@ModelAttribute ProductDTOFilter productDTOFilter) {
        return productService.searchProduct(productDTOFilter);
    }

    @GetMapping("/{id}")
    public ProductDTOResponse getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping()
    public ProductDTOResponse createProduct(@RequestBody ProductDTOCreate productDTOCreate) {
        return productService.createProduct(productDTOCreate);
    }

    @PutMapping("/{id}")
    public ProductDTOResponse updateProduct(@RequestBody ProductDTOUpdate productDTOUpdate,
                                            @PathVariable int id) {
        return productService.updateProduct(productDTOUpdate,id);
    }

    @DeleteMapping("/{id}")
    public ProductDTOResponse deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
