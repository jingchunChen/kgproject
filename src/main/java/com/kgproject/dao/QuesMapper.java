package com.kgproject.dao;

import com.kgproject.model.entity.Ques;
import com.kgproject.model.vo.QuesVO;

import java.util.List;

public interface QuesMapper {
    List<QuesVO> getQuesListByIdList(List<Integer> ids, String type);
    String getAnswerById(Integer id);
    QuesVO getQuesById(Integer id);
    Ques getDetailsById(Integer id);
}
