package org.example.baken.service.impl;


import org.example.baken.entity.PageResult;
import org.example.baken.entity.Product;
import org.example.baken.mapper.ProductMapper;
import org.example.baken.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Long id) {
        return productMapper.selectProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productMapper.selectAllProducts();
    }

    @Override
    public void addProduct(Product product) {
        // 设置创建时间和更新时间
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        productMapper.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        product.setUpdateTime(new Date());
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productMapper.deleteProductById(id);
    }

    @Override
    public PageResult<Product> getProductsByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize; // 计算偏移量
        List<Product> products = productMapper.selectProductsByPage(offset, pageSize); // 分页查询
        long total = productMapper.countProducts(); // 查询总记录数
        return new PageResult<>(pageNum, pageSize, total, products);
    }

    @Override
    public PageResult<Product> searchProducts(String name, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize; // 计算偏移量
        List<Product> products = productMapper.searchProducts(name, offset, pageSize); // 分页查询
        long total = productMapper.countSearchProducts(name); // 查询总记录数
        return new PageResult<>(pageNum, pageSize, total, products);
    }

}
