package com.kgproject.filter;

import com.alibaba.fastjson.JSON;
import com.kgproject.model.entity.LoginUser;
import com.kgproject.model.entity.ResponseResult;
import com.kgproject.utils.JwtUtil;
import com.kgproject.utils.RedisCache;
import com.kgproject.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AuthenticationException {
        // 获取token
        String token = request.getHeader("token");
        // 如果请求头中没有Authorization信息则直接放行了
        if(!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid;
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "token非法！");
            String json = JSON.toJSONString(result);
            WebUtils.renderString(response, json);
            return;
        }
        userid = claims.getSubject();
        //从redis中获取用户信息
        String redisKey = "login:" + userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)) {
            ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户未登录！");
            String json = JSON.toJSONString(result);
            WebUtils.renderString(response, json);
            return;
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
