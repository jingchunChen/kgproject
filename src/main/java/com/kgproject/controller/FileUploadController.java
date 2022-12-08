package com.kgproject.controller;

import com.kgproject.model.entity.ResponseResult;
import com.kgproject.model.entity.User;
import com.kgproject.utils.FileUploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
@RestController
public class FileUploadController extends BaseController{

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    @PostMapping("/uploadAvatar")
    public ResponseResult uploadAvatar(@RequestPart("file") MultipartFile uploadFile, HttpServletRequest request) throws Exception {
        ResponseResult result = FileUploadUtil.upload(uploadFile, request, "avatar/");
        if(result.getCode() == 500) return result;
        String fileName = (String) result.getData().get("fileName");
        // 将用户上传的头像上传到阿里云oss对象容器
        String url = ossUtil.uploadFile(fileName);
        String userid = getUserIdByToken(request);
        User user = new User();
        // 上传数据库用户头像链接
        user.setAvatar(url);
        user.setId(userid);
        userService.modifyUserinfo(user);
        return ResponseResult.success("上传成功！");
    }

    @PostMapping("/uploadFile")
    public ResponseResult uploadFile(@RequestPart("file") MultipartFile uploadFile, HttpServletRequest request) throws FileNotFoundException {
        ResponseResult result = FileUploadUtil.upload(uploadFile, request, "userFile/");
        if(result.getCode() == 500) return result;
        String fileName = (String) result.getData().get("fileName");
        return ResponseResult.success("上传成功！").add("url", ossUtil.uploadFile(fileName));
    }
}
