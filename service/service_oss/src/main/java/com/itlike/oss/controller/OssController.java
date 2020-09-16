package com.itlike.oss.controller;

import com.itlike.oss.service.OssService;
import com.itlike.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;
    //上传头像的方法
    @PostMapping("/")
    public Result uploadOssFile(MultipartFile file){
        String url=ossService.uploadFileAvatar(file);
        //获取上传文件MultipartFile
        return Result.ok().data("url",url);
    }
}
