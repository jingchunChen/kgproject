package com.kgproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgproject.dao.PaperQuesMapper;
import com.kgproject.dao.QuesMapper;
import com.kgproject.dao.UserQuesMapper;
import com.kgproject.model.entity.Ques;
import com.kgproject.model.entity.UserQues;
import com.kgproject.model.vo.QuesVO;
import com.kgproject.service.PaperService;
import com.kgproject.service.UserQuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class UserQuesServiceImpl implements UserQuesService {

    @Autowired
    UserQuesMapper userQuesMapper;
    @Autowired
    PaperService paperService;
    @Autowired
    PaperQuesMapper paperQuesMapper;
    @Autowired
    QuesMapper quesMapper;
    @Override
    public List<UserQues> getUserQuesListByUserId(Integer id) {
        return userQuesMapper.searchByUserId(id);
    }
    /*
        根据用户id和试卷id查询用户的答题情况
     */
    @Override
    public PageInfo<QuesVO> getPaperRecordsByUserId(Integer paperId, String userId, String tab, Integer pageNum, Integer pageSize) {
        // 试卷的所有题目（未填充用户答题情况）
        if(tab.equals("0")) tab = null;
        List<Integer> quesIdList = paperQuesMapper.getQuesIdListByPaperId(paperId);
        PageHelper.startPage(pageNum, pageSize);
        List<QuesVO> paperQues = quesMapper.getQuesListByIdList(quesIdList, tab);
        HashMap<Integer, QuesVO> mp = new HashMap<>();
        // 用户的答题情况
        List<QuesVO> quesList = userQuesMapper.getQuesRecordsByPaperId(paperId, userId);
        for (QuesVO quesVO : quesList) {
            mp.put(quesVO.getId(), quesVO);
        }
        for (QuesVO ques : paperQues) {
            if(mp.containsKey(ques.getId())) {
                QuesVO quesVO = mp.get(ques.getId());
                ques.setStatus(quesVO.getStatus());
            }
        }
        return new PageInfo<>(paperQues);
    }

    @Override
    public QuesVO getQuesRecordByQuesId(Integer quesId, String userId) {
        // 如果用户回答过，则返回题目及作答记录
        QuesVO record = userQuesMapper.getQuesRecordByQuesId(quesId, userId);
        // 否则只返回题目
        if(record == null) return quesMapper.getQuesById(quesId);
        return record;
    }

    @Override
    public void insertUserQues(UserQues userQues) {
        userQuesMapper.insertUserQues(userQues);
    }
}
