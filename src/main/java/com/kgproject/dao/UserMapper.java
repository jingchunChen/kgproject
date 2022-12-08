package com.kgproject.dao;

import com.kgproject.model.entity.User;

public interface UserMapper {
    User findUserByUsername(String username);
    User findUserById(String id);
    void updateUser(User user);
    void insertUser(User user);
}
