package com.kgproject.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.kgproject.model.entity.LoginUser;
import com.kgproject.model.entity.User;
import com.kgproject.dao.UserMapper;
import com.kgproject.service.LoginService;
import com.kgproject.utils.JwtUtil;
import com.kgproject.utils.RedisCache;
import com.kgproject.model.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        return ResponseResult.success("登录成功！").add("token",jwt);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return ResponseResult.success("退出成功！");
    }

    @Override
    public ResponseResult register(User user) {
        String id;
        do {
            id = RandomUtil.randomNumbers(20);
        } while(userMapper.findUserById(id) != null);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setId(id).setPassword(encodePassword).setCreatetime(new Date())
                .setAvatar("https://kgproject.oss-cn-shenzhen.aliyuncs.com/avatar/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F");
        userMapper.insertUser(user);
        return ResponseResult.success("注册成功！");
    }


}
