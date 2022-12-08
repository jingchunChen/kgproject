package com.kgproject.service.impl;

import com.kgproject.dao.QuesMapper;
import com.kgproject.model.entity.Ques;
import com.kgproject.model.vo.QuesVO;
import com.kgproject.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuesServiceImpl implements QuesService {
    @Autowired
    QuesMapper quesMapper;

    @Override
    public Boolean compareAnswer(Integer id, String answer) {
        return quesMapper.getAnswerById(id).equals(answer);
    }

    @Override
    public QuesVO getQuesById(Integer id) {
        return quesMapper.getQuesById(id);
    }

    @Override
    public Ques getDetailsById(Integer id) {
        return quesMapper.getDetailsById(id);
    }

}
