package com.kgproject.service;

import com.github.pagehelper.PageInfo;
import com.kgproject.model.entity.UserQues;
import com.kgproject.model.vo.QuesVO;

import java.util.List;

public interface UserQuesService {
    List<UserQues> getUserQuesListByUserId(Integer id);
    PageInfo<QuesVO> getPaperRecordsByUserId(Integer paperId, String userId, String tab, Integer pageNum, Integer pageSize);
    QuesVO getQuesRecordByQuesId(Integer quesId, String userId);
    void insertUserQues(UserQues userQues);
}
