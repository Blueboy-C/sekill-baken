package org.example.baken.service;

import org.example.baken.entity.UserAddress;
import org.example.baken.mapper.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

public interface UserAddressService {

    List<UserAddress> getAddressesByUserId(Long userId);

    UserAddress getAddressById(Long id);

    void addAddress(UserAddress userAddress);

    void updateAddress(UserAddress userAddress);

    void deleteAddress(Long id);

    void setDefaultAddress(Long userId, Long addressId);

    UserAddress getDefaultAddressByUserId(Long userId);
}
