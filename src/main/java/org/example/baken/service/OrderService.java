package org.example.baken.service;

import org.example.baken.entity.Order;
import org.example.baken.entity.PageResult;

import java.util.List;

public interface OrderService {
    // 根据订单ID查询订单
    Order getOrderById(Long id);

    // 根据用户ID查询订单列表
    List<Order> getOrdersByUserId(Long userId);

    // 分页查询订单
    PageResult<Order> getOrdersByPage(int pageNum, int pageSize);

    // 根据关键字搜索订单
    List<Order> searchOrders(String keyword);

    // 创建订单
    void createOrder(Order order);

    // 更新订单状态
    void updateOrderStatus(Long id, Integer status);

    // 查询所有订单
    List<Order> getAllOrders();
}