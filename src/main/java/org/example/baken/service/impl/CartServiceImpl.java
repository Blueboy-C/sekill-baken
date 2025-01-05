package org.example.baken.service.impl;

import org.example.baken.entity.Cart;
import org.example.baken.mapper.CartMapper;
import org.example.baken.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

// CartServiceImpl 实现类
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addToCart(Cart cart) {
        Cart existingCartItem = cartMapper.selectCartItem(cart.getUserId(), cart.getProductId());
        if (existingCartItem != null) {
            // 如果商品已存在，更新数量
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cart.getQuantity());
            cartMapper.updateCartItemQuantity(existingCartItem.getId(), existingCartItem.getQuantity());
        } else {
            // 如果商品不存在，新增
            cart.setCreateTime(new Date());
            cart.setUpdateTime(new Date());
            cartMapper.addToCart(cart);
        }
    }

    @Override
    public void updateCartItemQuantity(Long id, Integer quantity) {
        cartMapper.updateCartItemQuantity(id, quantity);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartMapper.deleteCartItem(id);
    }


    @Override
    public List<Cart> getCartByUserId(Long userId) {
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        // 直接输出购物车信息到控制台
        for (Cart cartItem : cartList) {

            System.out.println("Cart Item: " + cartItem.toString());
        }
        return cartList;
    }
}
