package com.kgproject.dao;

import com.kgproject.model.entity.Jsonmind;
import org.apache.ibatis.annotations.Param;

public interface MindMapper {
    Jsonmind getMind(@Param("userid") String userid, @Param("name") String name);
    void insertMind(Jsonmind jm);
    void saveMind(Jsonmind jm);
    Jsonmind getDefaultMind(String name);
}
