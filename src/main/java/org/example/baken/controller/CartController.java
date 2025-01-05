package org.example.baken.controller;

import org.example.baken.entity.Cart;
import org.example.baken.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 添加商品到购物车
    @PostMapping("/add")
    public void addToCart(@RequestBody Cart cart) {
        cartService.addToCart(cart);
    }

    @PostMapping("/update")
    public void updateCartItemQuantity(@RequestBody Map<String, Object> request) {
        Long id = Long.parseLong(request.get("id").toString());
        Integer quantity = Integer.parseInt(request.get("quantity").toString());
        cartService.updateCartItemQuantity(id, quantity);
    }

    // 删除购物车中的商品
    @PostMapping("/delete")
    public void deleteCartItem(@RequestParam Long id) {
        cartService.deleteCartItem(id);
    }

    // 根据用户ID查询购物车列表
    @GetMapping("/list")
    public List<Cart> getCartByUserId(@RequestParam Long userId) {
        List<Cart> cartList = cartService.getCartByUserId(userId);
        System.out.println(cartList);
        return cartService.getCartByUserId(userId);
    }
}
