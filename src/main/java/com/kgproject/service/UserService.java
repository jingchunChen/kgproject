package com.kgproject.service;

import com.kgproject.model.entity.User;
import com.kgproject.model.vo.HomepageVO;
import com.kgproject.model.vo.ProfileVO;

import java.util.Map;

public interface UserService {
    void modifyUserinfo(User user);
    HomepageVO displayUserinfo(String id);
    User getUserByUsername(String username);
    User getUserById(String id);
    void setPassword(User user);
    ProfileVO getUserProfile(String id);
}
