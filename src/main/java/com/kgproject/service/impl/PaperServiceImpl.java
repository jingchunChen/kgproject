package com.kgproject.service.impl;

import com.kgproject.dao.PaperMapper;
import com.kgproject.dao.PaperQuesMapper;
import com.kgproject.dao.QuesMapper;
import com.kgproject.dao.UserQuesMapper;
import com.kgproject.model.entity.Paper;
import com.kgproject.model.entity.Ques;
import com.kgproject.model.entity.UserQues;
import com.kgproject.model.vo.QuesVO;
import com.kgproject.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperMapper paperMapper;
    @Autowired
    PaperQuesMapper paperQuesMapper;
    @Autowired
    QuesMapper quesMapper;
    @Autowired
    UserQuesMapper userQuesMapper;

    /*
        根据试卷id查询所有题目
     */
    @Override
    public List<QuesVO> searchQuesListByPaperId(Integer id, String type) {
        List<Integer> quesIdList = paperQuesMapper.getQuesIdListByPaperId(id);
        return quesMapper.getQuesListByIdList(quesIdList, type);
    }
}
