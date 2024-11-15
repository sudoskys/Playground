package com.star.demo.controller;

import com.star.demo.model.Product;
import com.star.demo.service.ProductService;
import com.star.demo.common.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ApiResponse<Product> createProduct(@RequestBody Product product) {
        boolean success = productService.save(product);
        if (!success) {
            return ApiResponse.error("创建商品失败");
        }
        return ApiResponse.success(product);
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product == null) {
            return ApiResponse.error("商品不存在");
        }
        return ApiResponse.success(product);
    }

    @GetMapping
    public ApiResponse<Page<Product>> getAllProducts(Pageable pageable) {
        Page<Product> products = productService.getAll(pageable);
        return ApiResponse.success(products);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProductById(@PathVariable Long id) {
        boolean success = productService.deleteById(id);
        if (!success) {
            return ApiResponse.error("删除商品失败");
        }
        return ApiResponse.success(null);
    }
}