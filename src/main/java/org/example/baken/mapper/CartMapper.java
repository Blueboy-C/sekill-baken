package org.example.baken.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.baken.entity.Cart;

import java.util.List;

@Mapper
public interface CartMapper {

    // 添加商品到购物车
    void addToCart(Cart cart);

    // 更新购物车中商品数量
    void updateCartItemQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);

    // 删除购物车中的商品
    void deleteCartItem(Long id);

    // 根据用户ID查询购物车列表
    List<Cart> selectCartByUserId(Long userId);

    // 根据用户ID和商品ID查询购物车项
    Cart selectCartItem(@Param("userId") Long userId, @Param("productId") Long productId);
}
