package com.kgproject.service;

import com.kgproject.model.entity.Ques;
import com.kgproject.model.vo.QuesVO;

public interface QuesService {
    Boolean compareAnswer(Integer id, String answer);
    QuesVO getQuesById(Integer id);
    Ques getDetailsById(Integer id);
}
