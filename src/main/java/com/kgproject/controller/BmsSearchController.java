package com.kgproject.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kgproject.model.entity.ResponseResult;
import com.kgproject.model.vo.PostVO;
import com.kgproject.service.IBmsPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/searchList")
public class BmsSearchController extends BaseController {

    @GetMapping
    public ResponseResult searchList(@RequestParam("keyword") String keyword,
                                                   @RequestParam("pageNum") Integer pageNum,
                                                   @RequestParam("pageSize") Integer pageSize) {
        Page<PostVO> results = postService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return ResponseResult.success("获取搜索结果成功！").add("results", results);
    }

}
