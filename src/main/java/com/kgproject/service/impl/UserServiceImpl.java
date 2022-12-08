package com.kgproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kgproject.dao.BmsFollowMapper;
import com.kgproject.dao.BmsTopicMapper;
import com.kgproject.model.entity.BmsFollow;
import com.kgproject.model.entity.BmsPost;
import com.kgproject.model.entity.User;
import com.kgproject.dao.UserMapper;
import com.kgproject.model.vo.HomepageVO;
import com.kgproject.model.vo.ProfileVO;
import com.kgproject.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private BmsTopicMapper bmsTopicMapper;
    @Autowired
    private BmsFollowMapper bmsFollowMapper;
    @Override
    public void modifyUserinfo(User user) {
        // 不允许在修改用户信息处修改密码
        user.setPassword(null);
        userMapper.updateUser(user);
    }

    @Override
    public HomepageVO displayUserinfo(String id) {
        User user = userMapper.findUserById(id);
        HomepageVO homePage = new HomepageVO();
        BeanUtils.copyProperties(user, homePage);
        return homePage;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void setPassword(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profile = new ProfileVO();
        User user = userMapper.findUserById(id);
        BeanUtils.copyProperties(user, profile);
        // 用户文章数
        int count = bmsTopicMapper.selectCount(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, id));
        profile.setTopicCount(count);

        // 粉丝数
        int followers = bmsFollowMapper.selectCount((new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, id)));
        profile.setFollowerCount(followers);

        return profile;
    }


}
