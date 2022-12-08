package com.kgproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgproject.model.dto.CommentDTO;
import com.kgproject.model.entity.BmsComment;
import com.kgproject.model.entity.User;
import com.kgproject.model.vo.CommentVO;

import java.util.List;


public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, User principal);
}
