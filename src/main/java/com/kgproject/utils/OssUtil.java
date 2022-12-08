package com.kgproject.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
public class OssUtil {
    //阿里云OSS地址，这里看根据你的oss选择
    @Value("${oss.endpoint}")
    private String endpoint;
    //阿里云OSS账号
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    //阿里云OSS密钥
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    //阿里云OSS上的存储块bucket名字
    @Value("${oss.bucketName}")
    private String bucketName;

    public String uploadFile(String objectPath) throws FileNotFoundException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String url = null;
        try {
            //InputStream的默认路径是项目根目录
            String filePath = "src/main/resources/static/uploadFile/" + objectPath;
            InputStream fileStream = new FileInputStream(filePath);
            ossClient.putObject(bucketName, objectPath, fileStream);
            url = "https://kgproject.oss-cn-shenzhen.aliyuncs.com/" + objectPath;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
}
