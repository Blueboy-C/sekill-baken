package org.example.baken.mapper;




import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.baken.entity.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    // 根据订单ID查询订单及其关联的商品信息
    Order selectOrderById(Long id);

    // 根据用户ID查询订单列表及其关联的商品信息
    List<Order> selectOrdersByUserId(Long userId);

    // 分页查询订单列表及其关联的商品信息
    List<Order> selectOrdersByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 查询订单总数
    long countOrders();

    // 根据关键字搜索订单列表及其关联的商品信息
    List<Order> searchOrders(@Param("keyword") String keyword);

    // 插入订单
    void insertOrder(Order order);

    // 更新订单状态
    void updateOrderStatus(@Param("id") Long id, @Param("status") Integer status);

    // 查询所有订单及其关联的商品信息
    List<Order> selectAllOrders();
}