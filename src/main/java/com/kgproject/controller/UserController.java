package com.kgproject.controller;

import com.kgproject.model.entity.ResponseResult;
import com.kgproject.model.entity.User;
import com.kgproject.model.vo.HomepageVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @PostMapping("/modify")
    public ResponseResult modifyUserinfo(@RequestBody User user, HttpServletRequest request) throws Exception {
        String userid = getUserIdByToken(request);
        User tarUser = userService.getUserByUsername(user.getUsername());
        if(tarUser != null && !tarUser.getId().equals(userid)) {
            return ResponseResult.fail("该用户名已存在！");
        }
        user.setId(userid);
        userService.modifyUserinfo(user);
        return ResponseResult.success("修改成功！");
    }
    /*
        传入json格式：
        {
            "oldPassword":""
            "newPassword":""
        }
     */
    @PostMapping("/changePassword")
    public ResponseResult changerUserPassword(@RequestBody Map<String, Object> password, HttpServletRequest request) throws Exception {
        String userid = getUserIdByToken(request);
        User user = userService.getUserById(userid);
        if(!passwordEncoder.matches((String)password.get("oldPassword"), user.getPassword())) {
            return ResponseResult.fail("密码错误！");
        }
        String encodeNewPassword = passwordEncoder.encode((String)password.get("newPassword"));
        User tmpUser = new User();
        tmpUser.setId(userid);
        tmpUser.setPassword(encodeNewPassword);
        userService.setPassword(tmpUser);
        return ResponseResult.success("修改密码成功！");
    }

    @GetMapping("/display")
    public ResponseResult displayUserinfo(HttpServletRequest request) throws Exception {
        String userid = getUserIdByToken(request);
        HomepageVO userinfo = userService.displayUserinfo(userid);
        return ResponseResult.success("查询个人信息成功！").add("userinfo", userinfo);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        User oldUser = userService.getUserByUsername(user.getUsername());
        if(oldUser != null) {
            return ResponseResult.fail("该用户名已存在！");
        }
        return loginService.register(user);
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @RequestMapping("/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
