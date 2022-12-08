package com.kgproject.dao;

import com.kgproject.model.entity.UserQues;
import com.kgproject.model.vo.QuesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserQuesMapper {
    List<UserQues> searchByUserId(Integer id);
    List<QuesVO> getQuesRecordsByPaperId(@Param("paperId") Integer paperId, @Param("userId") String userId);
    QuesVO getQuesRecordByQuesId(@Param("quesId") Integer quesId, @Param("userId") String userId);
    void insertUserQues(UserQues userQues);
}
