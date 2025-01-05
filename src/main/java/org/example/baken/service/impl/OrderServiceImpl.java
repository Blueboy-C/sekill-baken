package org.example.baken.service.impl;

import org.example.baken.entity.Order;
import org.example.baken.entity.PageResult;
import org.example.baken.mapper.OrderMapper;
import org.example.baken.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.selectOrderById(id);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderMapper.selectOrdersByUserId(userId);
    }

    @Override
    public PageResult<Order> getOrdersByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<Order> orders = orderMapper.selectOrdersByPage(offset, pageSize);
        long total = orderMapper.countOrders();
        return new PageResult<>(pageNum, pageSize, total, orders);
    }

    @Override
    public List<Order> searchOrders(String keyword) {
        return orderMapper.searchOrders(keyword);
    }

    @Override
    public void createOrder(Order order) {
        // 设置创建时间和更新时间
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setStatus(0);

        // 插入订单
        orderMapper.insertOrder(order);
    }

    @Override
    public void updateOrderStatus(Long id, Integer status) {
        // 更新订单状态
        orderMapper.updateOrderStatus(id, status);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.selectAllOrders();
    }
}