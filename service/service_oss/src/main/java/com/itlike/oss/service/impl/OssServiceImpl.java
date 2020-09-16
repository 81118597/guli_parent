package com.itlike.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.itlike.oss.service.OssService;
import com.itlike.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtils.END_POIND, ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET);
            //上传文件流
            InputStream inputStream=file.getInputStream();
            //获取文件名称
            String originalFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            String fileName=datePath+"/"+uuid+originalFilename;
            //调用oss方法实现上传
            ossClient.putObject(ConstantPropertiesUtils.BUCKET_MAME,fileName,inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            String url="https://"+ConstantPropertiesUtils.BUCKET_MAME+"."+ConstantPropertiesUtils.END_POIND+"/"+fileName;
            System.out.println(url);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
