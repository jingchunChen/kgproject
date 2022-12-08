package com.kgproject.controller;

import com.github.pagehelper.PageInfo;
import com.kgproject.model.entity.Ques;
import com.kgproject.model.entity.ResponseResult;
import com.kgproject.model.entity.UserQues;
import com.kgproject.model.vo.QuesVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/paper/{paperId}")
public class PaperController extends BaseController {
    @GetMapping("")
    public ResponseResult getPaperById(
            @PathVariable("paperId") Integer paperId,
            @RequestParam(value = "tab", defaultValue = "0") String tab,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HttpServletRequest request) throws Exception {
        String userId = getUserIdByToken(request);
        //紧跟着的第一个select方法会被分页
        PageInfo<QuesVO> pageInfo = userQuesService.getPaperRecordsByUserId(paperId, userId, tab, pageNum, pageSize);
        return ResponseResult.success("获取用户试卷成功！").add("paper", pageInfo);
    }

    @GetMapping("/{quesId}")
    public ResponseResult getQuesById(
            @PathVariable("quesId") Integer quesId,
            HttpServletRequest request) throws Exception {
        String userId = getUserIdByToken(request);
        QuesVO quesVO = userQuesService.getQuesRecordByQuesId(quesId, userId);
        // 如果用户回答过，返回答案
        Ques details = quesService.getDetailsById(quesId);
        if(quesVO.getStatus() == null)
            return ResponseResult.success("获取题目成功！").add("ques", quesVO);
        else
            return ResponseResult.success("获取题目成功！").add("ques", quesVO).add("details", details);
    }

    @GetMapping("/submit")
    public ResponseResult quesSubmit(
            @RequestParam("quesId") Integer quesId,
            @RequestParam("answer") String answer,
            HttpServletRequest request) throws Exception {
        String userId = getUserIdByToken(request);
        UserQues userQues = new UserQues();
        // 如果用户回答过，返回答案
        Ques details = quesService.getDetailsById(quesId);
        if(!quesService.compareAnswer(quesId, answer)) {
            userQues.setUserId(userId).setQuesId(quesId).setStatus("回答错误").setAnswer(answer);
            userQuesService.insertUserQues(userQues);
            return ResponseResult.success("回答错误").add("status", "F").add("details", details);
        }
        else {
            userQues.setUserId(userId).setQuesId(quesId).setStatus("已完成").setAnswer(answer);
            userQuesService.insertUserQues(userQues);
            return ResponseResult.success("回答正确").add("status", "T").add("details", details);
        }
    }

}
