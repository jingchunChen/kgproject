package com.kgproject.service.impl;

import com.kgproject.model.entity.Jsonmind;
import com.kgproject.dao.MindMapper;
import com.kgproject.service.MindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MindServiceImpl implements MindService {
    @Autowired
    MindMapper mindMapper;
    @Override
    public Jsonmind getMind(String userid, String name) {
        Jsonmind mind = mindMapper.getMind(userid, name);
        if(mind == null) {
            mind = mindMapper.getDefaultMind(name);
            mind.setUserid(userid);
            mindMapper.insertMind(mind);
        }
        return mind;
    }

    @Override
    public void saveMind(Jsonmind jm) {
        Jsonmind mind = mindMapper.getMind(jm.getUserid(), jm.getName());
        if(mind != null) mindMapper.saveMind(jm);
        else mindMapper.insertMind(jm);
    }
}
