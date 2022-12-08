package com.kgproject.service;

import com.kgproject.model.entity.User;
import com.kgproject.model.entity.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);
    ResponseResult logout();
    ResponseResult register(User user);
}
