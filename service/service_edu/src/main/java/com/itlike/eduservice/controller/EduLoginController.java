package com.itlike.eduservice.controller;

import com.itlike.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class EduLoginController {
    Logger logger= LoggerFactory.getLogger(Logger.class);
    @PostMapping("/login")
    public Result login(){
        UUID uuid=UUID.randomUUID();
        String u=uuid.toString();
        logger.info("token"+u);
        return Result.ok().data("token",u);
    }
    @GetMapping("/info")
    public Result info(){
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://guli-deu-pro.oss-cn-shanghai.aliyuncs.com/2020/08/21/2401f70ba5104e8eaf3c012611708db5file.png");
    }
}
