package org.example.baken.service.impl;

import org.example.baken.entity.PageResult;
import org.example.baken.entity.User;
import org.example.baken.mapper.UserMapper;
import org.example.baken.service.EmailService;
import org.example.baken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;

    private Map<String, String> verificationCodes = new HashMap<>(); // 存储邮箱和验证码的映射

    @Override
    public void sendVerificationCode(String email) {
        // 生成随机验证码
        String code = String.valueOf((int) (Math.random() * 900000) + 100000);
        verificationCodes.put(email, code); // 存储验证码

        // 发送验证码到邮箱
        emailService.sendVerificationCode(email, code);
    }

    @Override
    public boolean verifyCode(String email, String code) {
        // 校验验证码
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }

    @Override
    public void registerUser(User user, String code) {
        // 校验验证码
        if (!verifyCode(user.getEmail(), code)) {
            throw new IllegalArgumentException("验证码不正确或已过期");
        }

        // 校验用户信息
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("邮箱不能为空");
        }

        // 设置默认角色
        if (user.getRole() == null) {
            user.setRole(0); // 默认角色为普通用户
        }

        // 保存用户信息
        userMapper.insertUser(user);

        // 清除验证码
        verificationCodes.remove(user.getEmail());
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public void registerUser(User user) {
        if(user.getRole()==null) user.setRole(0);
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void resetPassword(String email, String newPassword) {

    }

    @Override
    public boolean checkPassword(Long id, String oldPassword) {
        User user = userMapper.selectUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        return oldPassword.equals(user.getPassword()); // 校验旧密码是否正确
    }

    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        // 校验旧密码
        if (!checkPassword(id, oldPassword)) {
            throw new IllegalArgumentException("旧密码不正确");
        }

        // 校验新密码是否符合要求
        if (newPassword == null || newPassword.length() < 8) {
            throw new IllegalArgumentException("新密码长度不能少于8位");
        }

        // 更新密码
        userMapper.updatePassword(id, newPassword);
    }

    @Override
    public void resetPasswordWithCode(String email, String code, String newPassword) {
        // 校验验证码
        String storedCode = verificationCodes.get(email);
        if (storedCode == null || !storedCode.equals(code)) {
            throw new IllegalArgumentException("验证码不正确或已过期");
        }

        // 校验新密码
        if (newPassword == null || newPassword.length() < 8) {
            throw new IllegalArgumentException("新密码长度不能少于8位");
        }

        // 更新密码
        User user = userMapper.selectUserByEmail(email);
        if (user != null) {
            userMapper.updatePassword(user.getId(), newPassword);
        }

        // 清除验证码
        verificationCodes.remove(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public PageResult<User> searchUsers(String query, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize; // 计算偏移量
        List<User> users = userMapper.searchUsers(query, offset, pageSize); // 分页查询
        long total = userMapper.countSearchUsers(query); // 查询总记录数
        return new PageResult<>(pageNum, pageSize, total, users);
    }

    @Override
    public PageResult<User> getUsersByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<User> data = userMapper.selectUsersByPage(offset, pageSize);
        long total = userMapper.selectTotalCount();
        return new PageResult<>(pageNum, pageSize, total, data);
    }
}