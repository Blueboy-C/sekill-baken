package org.example.baken.controller;

import org.example.baken.entity.UserAddress;
import org.example.baken.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    // 查询用户的收货地址列表
    @GetMapping("/list")
    public List<UserAddress> listAddresses(@RequestParam Long userId) {
        return userAddressService.getAddressesByUserId(userId);
    }

    // 查询收货地址详情
    @GetMapping("/detail")
    public UserAddress getAddressDetail(@RequestParam Long id) {
        return userAddressService.getAddressById(id);
    }

    // 添加收货地址
    @PostMapping("/add")
    public void addAddress(@RequestBody UserAddress userAddress) {
        userAddressService.addAddress(userAddress);
    }

    // 更新收货地址
    @PostMapping("/update")
    public void updateAddress(@RequestBody UserAddress userAddress) {
        userAddressService.updateAddress(userAddress);
    }

    // 删除收货地址
    @PostMapping("/delete")
    public void deleteAddress(@RequestParam Long id) {
        userAddressService.deleteAddress(id);
    }

    @PostMapping("/setDefault")
    public void setDefaultAddress(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long addressId = request.get("addressId");
        userAddressService.setDefaultAddress(userId, addressId);
    }
    // 查询用户的默认地址
    @GetMapping("/default")
    public UserAddress getDefaultAddress(@RequestParam Long userId) {
        return userAddressService.getDefaultAddressByUserId(userId);
    }
}
