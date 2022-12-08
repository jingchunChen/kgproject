package com.kgproject.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kgproject.model.dto.CreateTopicDTO;
import com.kgproject.model.entity.BmsPost;
import com.kgproject.model.entity.ResponseResult;
import com.kgproject.model.entity.User;
import com.kgproject.model.vo.PostVO;
import com.kgproject.service.IBmsPostService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @GetMapping("/list")
    public ResponseResult list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                               @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                               @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iBmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ResponseResult.success("获取所有帖子成功！").add("list", list);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseResult create(@RequestBody CreateTopicDTO dto, HttpServletRequest request) throws Exception {
        String userid = getUserIdByToken(request);
        User user = userService.getUserById(userid);
        BmsPost topic = iBmsPostService.create(dto, user);
        return ResponseResult.success("创建帖子成功！").add("topic", topic);
    }
    @GetMapping()
    public ResponseResult view(@RequestParam("id") String id) {
        Map<String, Object> map = iBmsPostService.viewTopic(id);
        return ResponseResult.success("查看帖子成功！").addAll(map);
    }

    @GetMapping("/recommend")
    public ResponseResult getRecommend(@RequestParam("topicId") String id) {
        //除了当前查看的帖子外，获取推荐贴
        List<BmsPost> topics = iBmsPostService.getRecommend(id);
        return ResponseResult.success("获取随便看看成功！").add("topics", topics);
    }

    @PostMapping("/update")
    public ResponseResult update(@Valid @RequestBody BmsPost post, HttpServletRequest request) throws Exception {
        String userid = getUserIdByToken(request);
        User user = userService.getUserById(userid);
        Assert.isTrue(user.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        iBmsPostService.updateById(post);
        return ResponseResult.success("更新帖子成功！").add("post", post);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete( @PathVariable("id") String id, HttpServletRequest request) throws Exception {
        String userid = getUserIdByToken(request);
        User user = userService.getUserById(userid);
        BmsPost byId = iBmsPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(user.getId()), "你为什么可以删除别人的话题？？？");
        iBmsPostService.removeById(id);
        return ResponseResult.success("删除成功");
    }

}
