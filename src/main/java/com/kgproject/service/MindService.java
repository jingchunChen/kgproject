package com.kgproject.service;

import com.kgproject.model.entity.Jsonmind;

public interface MindService {
    Jsonmind getMind(String userid, String name);
    void saveMind(Jsonmind jm);
}
