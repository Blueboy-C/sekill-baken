package org.example.baken.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.baken.entity.UserAddress;

import java.util.List;

@Mapper
public interface UserAddressMapper {

    // 根据用户ID查询收货地址列表
    List<UserAddress> selectAddressesByUserId(Long userId);

    // 根据地址ID查询收货地址
    UserAddress selectAddressById(Long id);

    // 插入收货地址
    void insertAddress(UserAddress userAddress);

    // 更新收货地址
    void updateAddress(UserAddress userAddress);

    // 删除收货地址
    void deleteAddress(Long id);

    // 设置默认地址
    void setDefaultAddress(@Param("userId") Long userId, @Param("addressId") Long addressId);

    // 查询用户的默认地址
    UserAddress selectDefaultAddressByUserId(Long userId);
}