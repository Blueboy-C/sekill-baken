package org.example.baken.service;


import org.example.baken.entity.PageResult;
import org.example.baken.entity.User;

import java.util.List;

public interface UserService {
    void sendVerificationCode(String email);

    User getUserById(Long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    void registerUser(User user);
    void updateUser(User user);
    void resetPassword(String email, String newPassword);
    void resetPasswordWithCode(String email, String code, String newPassword);// 发送验证码
    boolean verifyCode(String email, String code); // 校验验证码
    void registerUser(User user, String code); // 注册用户时校验验证码
    List<User> getAllUsers();
    void deleteUserById(Long id);
    PageResult<User> searchUsers(String query, int pageNum, int pageSize);
    PageResult<User> getUsersByPage(int pageNum, int pageSize);
    boolean checkPassword(Long id, String oldPassword); // 校验旧密码是否正确
    void updatePassword(Long id, String oldPassword, String newPassword); // 更新密码时校验
}
