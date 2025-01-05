package org.example.baken.service;

import org.example.baken.entity.PageResult;
import org.example.baken.entity.SeckillOrder;
import org.example.baken.mapper.SeckillOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    // 创建订单
    public void createSeckillOrder(SeckillOrder seckillOrder) {
        seckillOrder.setStatus(0); // 初始状态为待支付
        seckillOrder.setCreateTime(new Date());
        seckillOrderMapper.insertSeckillOrder(seckillOrder);
    }

    // 取消订单
    public void cancelSeckillOrder(Long orderId) {
        seckillOrderMapper.cancelSeckillOrder(orderId);
    }

    // 支付订单
    public void paySeckillOrder(Long orderId) {
        seckillOrderMapper.paySeckillOrder(orderId);
    }

    // 根据订单ID查询订单详情
    public SeckillOrder getSeckillOrderById(Long orderId) {
        return seckillOrderMapper.selectSeckillOrderById(orderId);
    }


    // 根据用户ID查询订单列表（分页）
    // 根据用户ID查询订单列表（分页 + 搜索 + 过滤）
    public PageResult<SeckillOrder> getSeckillOrdersByUserId(
            Long userId, int page, int size, String search, Integer status
    ) {
        // 计算分页偏移量
        int offset = (page - 1) * size;

        // 查询当前页的数据
        List<SeckillOrder> orders = seckillOrderMapper.selectSeckillOrdersByUserId(
                userId, offset, size, search, status
        );

        // 查询总记录数
        int total = seckillOrderMapper.countSeckillOrdersByUserId(userId, search, status);

        // 返回分页结果
        return new PageResult<>(size, total, page, orders);
    }

    public PageResult<SeckillOrder> getSeckillOrdersByAdmin(int page, int size, String search, Integer status) {
        // 计算分页偏移量
        int offset = (page - 1) * size;

        // 查询当前页的数据
        List<SeckillOrder> orders = seckillOrderMapper.selectSeckillOrdersByAdmin(
                offset, size, search, status
        );

        // 查询总记录数
        int total = seckillOrderMapper.countSeckillOrders(search, status);

        // 返回分页结果
        return new PageResult<>(size, total, page, orders);
    }
}
