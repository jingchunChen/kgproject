package com.kgproject.controller;

import com.kgproject.model.entity.Jsonmind;
import com.kgproject.model.entity.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/jm")
public class mindController extends BaseController{
    @GetMapping("/getMind")
    ResponseResult getMind(HttpServletRequest request, String name) throws Exception {
        String userid = getUserIdByToken(request);
        Jsonmind mind = mindService.getMind(userid, name);
        if(mind == null) return ResponseResult.fail("获取失败！");
        return ResponseResult.success("获取成功！").add("jm", mind);
    }
    @PostMapping("/saveMind")
    ResponseResult saveMind(HttpServletRequest request, @RequestBody Map<String, String> jm) throws Exception {
        /*
            {
                "name": ...
                "content": ...
            }
         */
        String userid = getUserIdByToken(request);
        Jsonmind jsonmind = new Jsonmind(userid, jm.get("name"), jm.get("content"));
        mindService.saveMind(jsonmind);
        return ResponseResult.success("保存成功！");
    }
}
