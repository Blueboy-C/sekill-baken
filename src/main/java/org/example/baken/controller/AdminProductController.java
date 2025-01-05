package org.example.baken.controller;

import org.example.baken.entity.ApiResponse;
import org.example.baken.entity.PageResult;
import org.example.baken.entity.Product;
import org.example.baken.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
    // 其他接口...

    @Autowired
    private ProductService productService;
    @GetMapping
    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ApiResponse.success(products);
    }

    @PostMapping
    public ApiResponse<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ApiResponse.success("Product added successfully");
    }

    @PutMapping("/{id}")
    public ApiResponse<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return ApiResponse.success("Product updated successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ApiResponse.success("Product deleted successfully");
    }

    @GetMapping("/page")
    public ApiResponse<PageResult<Product>> getProductsByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageResult<Product> result = productService.getProductsByPage(pageNum, pageSize);
        return ApiResponse.success(result);
    }
}