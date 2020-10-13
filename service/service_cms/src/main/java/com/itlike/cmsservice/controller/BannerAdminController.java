package com.itlike.cmsservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itlike.cmsservice.entity.BannerQuery;
import com.itlike.cmsservice.entity.CrmBanner;
import com.itlike.cmsservice.service.CrmBannerService;
import com.itlike.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author ZYQ
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/cmsservice/crm-banneradmin")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;
    //分页查询
    @PostMapping("/bannerPage/{current}/{limit}")
    public Result bannerPage(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) BannerQuery query){
        Page<CrmBanner> Page = new Page<>(current,limit);
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if(!StringUtils.isEmpty(query.getTitle())){
            queryWrapper.like("title",query.getTitle());
        }
        IPage<CrmBanner> iPage = bannerService.page(Page, queryWrapper);
        return Result.ok().data("rows",iPage.getRecords()).data("total",iPage.getTotal());
    }
    //添加
    @PostMapping("/addbanner")
    public Result addbanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return  Result.ok();
    }
    //修改
    @PostMapping("/updatebanner")
    public Result updatebanner(@RequestBody CrmBanner crmBanner){
        bannerService.updateById(crmBanner);
        return  Result.ok();
    }
    //查询
    @GetMapping("/bannerGet/{id}")
    public Result bannerGet(@PathVariable String id){
        CrmBanner banner = bannerService.getById(id);
        return  Result.ok().data("banner",banner);
    }
    //删除
    @DeleteMapping("/delbanner/{id}")
    public Result delbanner(@PathVariable String id){
        bannerService.removeById(id);
        return Result.ok();
    }


}

