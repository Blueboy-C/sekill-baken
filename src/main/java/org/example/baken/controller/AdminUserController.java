package org.example.baken.controller;

import org.example.baken.entity.PageResult;
import org.example.baken.entity.User;
import org.example.baken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);



    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return "User updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User deleted successfully";
    }


    /**
     * 根据用户名或 ID 搜索用户
     *
     * @param query 用户名或 ID
     * @return 匹配的用户列表
     */

    /**
     * 根据用户名或 ID 搜索用户（支持分页）
     *
     * @param query    搜索关键字
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return 分页后的用户列表
     */
    @GetMapping("/search")
    public ResponseEntity<PageResult<User>> searchUsers(
            @RequestParam String query,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageResult<User> result = userService.searchUsers(query, pageNum, pageSize);
        return ResponseEntity.ok(result);
    }

    /**
     * 分页查询用户
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return 分页后的用户列表
     */
    @GetMapping("/page")
    public ResponseEntity<PageResult<User>> getUsersByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println("分页查询：pageNum=" + pageNum + ", pageSize=" + pageSize);
        PageResult<User> result = userService.getUsersByPage(pageNum, pageSize);
        System.out.println("查询结果：" + result);
        return ResponseEntity.ok(result);
    }
}
