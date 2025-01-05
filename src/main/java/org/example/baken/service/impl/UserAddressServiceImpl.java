package org.example.baken.service.impl;

import org.example.baken.entity.UserAddress;
import org.example.baken.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.baken.mapper.UserAddressMapper;

import java.util.Date;
import java.util.List;

// UserAddressServiceImpl 实现类
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getAddressesByUserId(Long userId) {
        return userAddressMapper.selectAddressesByUserId(userId);
    }

    @Override
    public UserAddress getAddressById(Long id) {
        return userAddressMapper.selectAddressById(id);
    }

    @Override
    public void addAddress(UserAddress userAddress) {
        userAddress.setCreateTime(new Date());
        userAddress.setUpdateTime(new Date());
        userAddress.setIsDefault(false);
        userAddressMapper.insertAddress(userAddress);
    }

    @Override
    public void updateAddress(UserAddress userAddress) {
        userAddress.setUpdateTime(new Date());
        userAddressMapper.updateAddress(userAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        userAddressMapper.deleteAddress(id);
    }

    @Override
    public void setDefaultAddress(Long userId, Long addressId) {
        userAddressMapper.setDefaultAddress(userId, addressId);
    }

    @Override
    public UserAddress getDefaultAddressByUserId(Long userId) {
        return userAddressMapper.selectDefaultAddressByUserId(userId);
    }
}
