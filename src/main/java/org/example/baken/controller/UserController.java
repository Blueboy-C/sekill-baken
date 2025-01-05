package org.example.baken.controller;

import org.example.baken.entity.User;
import org.example.baken.service.UserService;
import org.example.baken.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(existingUser.getId(), existingUser.getRole());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", existingUser.getRole().toString());
            response.put("userId",existingUser.getId().toString());
            return response;
        }
        throw new RuntimeException("Invalid username or password");
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

//    @PostMapping("/register")
//    public String registerUser(@Valid @RequestBody User user) {
//        userService.registerUser(user);
//        return "User registered successfully";
//    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return "User updated successfully";
    }

    @PutMapping("/update-password/{id}")
    public String updatePassword(
            @PathVariable Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        userService.updatePassword(id, oldPassword, newPassword);
        return "Password updated successfully";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        userService.resetPassword(email, newPassword);
        return "Password reset successfully";
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User deleted successfully";
    }

    @PostMapping("/send-verification-code")
    public String sendVerificationCode(@RequestBody Map<String, String> requestParams) {
        String email = requestParams.get("email");
        userService.sendVerificationCode(email);
        return "验证码已发送";
    }

    @PostMapping("/reset-password-with-code")
    public String resetPasswordWithCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        String code = request.get("code");
        userService.resetPasswordWithCode(email, code, newPassword);
        return "密码重置成功";
    }


    // 注册用户
    @PostMapping("/register")
    public String registerUser(@RequestBody User user, @RequestParam String code) {
        userService.registerUser(user, code);
        return "注册成功";
    }
}