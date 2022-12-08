package com.kgproject.service;

import com.kgproject.model.entity.Paper;
import com.kgproject.model.entity.Ques;
import com.kgproject.model.vo.QuesVO;

import java.util.List;

public interface PaperService {
    List<QuesVO> searchQuesListByPaperId(Integer id, String type);
}
