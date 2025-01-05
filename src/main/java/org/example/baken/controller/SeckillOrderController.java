package org.example.baken.controller;

import org.example.baken.entity.PageResult;
import org.example.baken.entity.SeckillOrder;
import org.example.baken.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seckill/orders")
public class SeckillOrderController {

    @Autowired
    private SeckillOrderService seckillOrderService;

    // 创建订单
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody SeckillOrder seckillOrder) {
        seckillOrderService.createSeckillOrder(seckillOrder);
        return ResponseEntity.ok("订单创建成功");
    }

    // 取消订单
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        seckillOrderService.cancelSeckillOrder(orderId);
        return ResponseEntity.ok("订单取消成功");
    }

    // 支付订单
    @PutMapping("/{orderId}/pay")
    public ResponseEntity<String> payOrder(@PathVariable Long orderId) {
        seckillOrderService.paySeckillOrder(orderId);
        return ResponseEntity.ok("订单支付成功");
    }

    // 根据订单ID查询订单详情
    @GetMapping("/{orderId}")
    public ResponseEntity<SeckillOrder> getOrderById(@PathVariable Long orderId) {
        SeckillOrder order = seckillOrderService.getSeckillOrderById(orderId);
        return ResponseEntity.ok(order);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<PageResult<SeckillOrder>> getOrdersByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") int page, // 当前页码，默认为1
            @RequestParam(defaultValue = "10") int size, // 每页大小，默认为10
            @RequestParam(required = false) String search, // 搜索关键字（可选）
            @RequestParam(required = false) Integer status // 订单状态过滤（可选）
    ) {
        PageResult<SeckillOrder> orders = seckillOrderService.getSeckillOrdersByUserId(userId,page, size, search, status);
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/admin")
    public ResponseEntity<PageResult<SeckillOrder>> getOrdersByAdmin(
            @RequestParam(defaultValue = "1") int page, // 当前页码，默认为1
            @RequestParam(defaultValue = "10") int size, // 每页大小，默认为10
            @RequestParam(required = false) String search, // 搜索关键字（可选）
            @RequestParam(required = false) Integer status // 订单状态过滤（可选）
    ) {
        PageResult<SeckillOrder> orders = seckillOrderService.getSeckillOrdersByAdmin(page, size, search, status);
        return ResponseEntity.ok(orders);
    }
}
