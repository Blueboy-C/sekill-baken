package org.example.baken.controller;

import org.example.baken.entity.ApiResponse;
import org.example.baken.entity.Order;
import org.example.baken.entity.PageResult;
import org.example.baken.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ApiResponse<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ApiResponse.success(order);
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ApiResponse.success(orders);
    }

    @GetMapping("/page")
    public ApiResponse<PageResult<Order>> getOrdersByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageResult<Order> result = orderService.getOrdersByPage(pageNum, pageSize);
        return ApiResponse.success(result);
    }

    @GetMapping("/search")
    public ApiResponse<List<Order>> searchOrders(@RequestParam String keyword) {
        List<Order> orders = orderService.searchOrders(keyword);
        return ApiResponse.success(orders);
    }

    @PostMapping("/create")
    public ApiResponse<String> createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return ApiResponse.success("订单创建成功");
    }

    @PutMapping("/update-status/{id}")
    public ApiResponse<String> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam Integer status) {
        orderService.updateOrderStatus(id, status);
        return ApiResponse.success("订单状态更新成功");
    }
}
