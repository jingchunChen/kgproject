package com.kgproject.utils;

import com.kgproject.model.entity.ResponseResult;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUploadUtil {
    public static ResponseResult upload(@RequestPart("file") MultipartFile uploadFile, HttpServletRequest request, String filePath){
        if(uploadFile.isEmpty()){
            //返回选择文件提示
            return ResponseResult.fail("请选择上传文件!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/");
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/static/uploadFile/" + filePath);
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File file = new File(realPath + format);
        if(!file.isDirectory()){
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            return ResponseResult.success("上传成功!").add("fileName", filePath + format + newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.fail("上传失败!");
    }
}
