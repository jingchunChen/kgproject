package com.kgproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgproject.dao.BmsFollowMapper;
import com.kgproject.model.entity.BmsFollow;
import com.kgproject.service.IBmsFollowService;
import org.springframework.stereotype.Service;


@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
