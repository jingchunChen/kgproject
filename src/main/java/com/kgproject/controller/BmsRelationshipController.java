package com.kgproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kgproject.model.entity.BmsFollow;
import com.kgproject.model.entity.ResponseResult;
import com.kgproject.model.entity.User;
import com.kgproject.service.IBmsFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/relationship")
public class BmsRelationshipController extends BaseController {

    @GetMapping("/subscribe/{userId}")
    public ResponseResult handleFollow(@PathVariable("userId") String parentId, HttpServletRequest request) throws Exception {
        String userid = getUserIdByToken(request);
        User user = userService.getUserById(userid);
        if (parentId.equals(user.getId())) {
            return ResponseResult.fail("您脸皮太厚了，怎么可以关注自己呢 😮");
        }
        BmsFollow one = bmsFollowService.getOne(
                new LambdaQueryWrapper<BmsFollow>()
                        .eq(BmsFollow::getParentId, parentId)
                        .eq(BmsFollow::getFollowerId, user.getId()));
        if (!ObjectUtils.isEmpty(one)) {
            return ResponseResult.fail("已关注");
        }

        BmsFollow follow = new BmsFollow();
        follow.setParentId(parentId);
        follow.setFollowerId(user.getId());
        bmsFollowService.save(follow);
        return ResponseResult.success("关注成功!");
    }

    @GetMapping("/unsubscribe/{userId}")
    public ResponseResult handleUnFollow(@PathVariable("userId") String parentId, HttpServletRequest request) throws Exception {
        String userid = getUserIdByToken(request);
        User user = userService.getUserById(userid);
        BmsFollow one = bmsFollowService.getOne(
                new LambdaQueryWrapper<BmsFollow>()
                        .eq(BmsFollow::getParentId, parentId)
                        .eq(BmsFollow::getFollowerId, user.getId()));
        if (ObjectUtils.isEmpty(one)) {
            return ResponseResult.fail("未关注！");
        }
        bmsFollowService.remove(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, parentId)
                .eq(BmsFollow::getFollowerId, user.getId()));
        return ResponseResult.success("取关成功!");
    }

    @GetMapping("/validate/{topicUserId}")
    public ResponseResult isFollow(@PathVariable("topicUserId") String topicUserId, HttpServletRequest request) throws Exception {
        String userid = getUserIdByToken(request);
        User user = userService.getUserById(userid);
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if (!ObjectUtils.isEmpty(user)) {
            BmsFollow one = bmsFollowService.getOne(new LambdaQueryWrapper<BmsFollow>()
                    .eq(BmsFollow::getParentId, topicUserId)
                    .eq(BmsFollow::getFollowerId, user.getId()));
            if (!ObjectUtils.isEmpty(one)) {
                map.put("hasFollow", true);
            }
        }
        String msg = (Boolean)map.get("hasFollow") ? "已关注！" : "未关注！";
        return ResponseResult.success(msg).addAll(map);
    }
}
