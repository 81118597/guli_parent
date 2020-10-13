package com.itlike.cmsservice.controller;


import com.itlike.cmsservice.entity.CrmBanner;
import com.itlike.cmsservice.service.CrmBannerService;
import com.itlike.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author ZYQ
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/cmsservice/crm-bannerfront")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class BannerFrontController{
    @Autowired
    private CrmBannerService bannerService;
    @GetMapping("/bannerAll")
    public Result bannerAll(){
        List<CrmBanner> list=bannerService.bannerAll();
        return Result.ok().data("list",list);
    }
}

