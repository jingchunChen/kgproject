package com.kgproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgproject.model.entity.BmsBillboard;
import com.kgproject.dao.BmsBillboardMapper;
import com.kgproject.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper
        , BmsBillboard> implements IBmsBillboardService {

}
