package org.example.baken.service;

import org.example.baken.entity.Cart;
import org.example.baken.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface CartService {

    void addToCart(Cart cart);

    void updateCartItemQuantity(Long id, Integer quantity);

    void deleteCartItem(Long id);

    List<Cart> getCartByUserId(Long userId);
}