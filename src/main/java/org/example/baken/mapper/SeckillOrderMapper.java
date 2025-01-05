package org.example.baken.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.baken.entity.SeckillOrder;

import java.util.List;

@Mapper
public interface SeckillOrderMapper {
    void insertSeckillOrder(SeckillOrder seckillOrder); // 插入秒杀订单
    SeckillOrder selectSeckillOrderByUserAndActivity(Long userId, Long seckillActivityId); // 根据用户和活动查询订单

    // 新增方法：取消订单
    void cancelSeckillOrder(Long orderId);

    // 新增方法：支付订单
    void paySeckillOrder(Long orderId);

    // 新增方法：根据订单ID查询订单详情
    SeckillOrder selectSeckillOrderById(Long orderId);

    // 根据用户ID查询订单列表（分页 + 搜索 + 过滤）
    List<SeckillOrder> selectSeckillOrdersByUserId(
            @Param("userId") Long userId,
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("search") String search,
            @Param("status") Integer status
    );

    // 根据用户ID查询订单总数（搜索 + 过滤）
    int countSeckillOrdersByUserId(
            @Param("userId") Long userId,
            @Param("search") String search,
            @Param("status") Integer status
    );

    List<SeckillOrder> selectSeckillOrdersByAdmin(int offset, int size, String search, Integer status);

    int countSeckillOrders(String search, Integer status);
}