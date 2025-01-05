package org.example.baken.service;

import org.example.baken.entity.PageResult;
import org.example.baken.entity.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProductById(Long id);
    /**
     * 分页查询商品
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return 分页后的商品列表
     */
    PageResult<Product> getProductsByPage(int pageNum, int pageSize);


    PageResult<Product> searchProducts(String name, int pageNum, int pageSize);
}
