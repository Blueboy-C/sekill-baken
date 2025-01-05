package org.example.baken.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.baken.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    Product selectProductById(Long id);
    List<Product> selectAllProducts();
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProductById(Long id);
    /**
     * 分页查询商品
     *
     * @param offset   偏移量
     * @param pageSize 每页大小
     * @return 分页后的商品列表
     */
    List<Product> selectProductsByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 查询商品总记录数
     *
     * @return 总记录数
     */
    long countProducts();

    /**
     * 根据名称搜索商品
     *
     * @param name     商品名称
     * @param offset   偏移量
     * @param pageSize 每页大小
     * @return 分页后的商品列表
     */
    List<Product> searchProducts(@Param("name") String name, @Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 统计搜索结果的记录数
     *
     * @param name 商品名称
     * @return 总记录数
     */
    long countSearchProducts(@Param("name") String name);
}
