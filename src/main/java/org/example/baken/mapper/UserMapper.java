package org.example.baken.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.baken.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectUserById(Long id);
    User selectUserByUsername(String username);
    User selectUserByEmail(String email);
    void insertUser(User user);
    void updateUser(User user);
    void updatePassword(Long id, String newPassword);

    List<User> selectAllUsers();

    void deleteUserById(Long id);

    /**
     * 根据用户名或 ID 搜索用户
     *
     * @param query 用户名或 ID
     * @return 匹配的用户列表
     */
    List<User> searchUsers(@Param("query") String query, @Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 分页查询用户
     *
     * @param offset   偏移量
     * @param pageSize 每页大小
     * @return 分页后的用户列表
     */
    List<User> selectUsersByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    long selectTotalCount();

    long countSearchUsers(@Param("query") String query);
}
