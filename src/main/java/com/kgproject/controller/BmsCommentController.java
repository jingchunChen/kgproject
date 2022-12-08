package com.kgproject.controller;

import com.kgproject.model.dto.CommentDTO;
import com.kgproject.model.entity.BmsComment;
import com.kgproject.model.entity.ResponseResult;
import com.kgproject.model.entity.User;
import com.kgproject.model.vo.CommentVO;
import com.kgproject.service.IBmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/comment")
public class BmsCommentController extends BaseController {

    @GetMapping("/get_comments")
    public ResponseResult getCommentsByTopicID(@RequestParam(value = "topicid", defaultValue = "1") String topicid) {
        List<CommentVO> lstBmsComment = bmsCommentService.getCommentsByTopicID(topicid);
        return ResponseResult.success("获取评论成功！").add("comment", lstBmsComment);
    }
    @PostMapping("/add_comment")
    public ResponseResult add_comment(@RequestBody CommentDTO dto, HttpServletRequest request) throws Exception {
        String userId = getUserIdByToken(request);
        User user = userService.getUserById(userId);
        BmsComment comment = bmsCommentService.create(dto, user);
        return ResponseResult.success("评论成功！").add("comment", comment);
    }
}
