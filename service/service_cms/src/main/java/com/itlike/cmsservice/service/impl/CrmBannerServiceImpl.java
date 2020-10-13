package com.itlike.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itlike.cmsservice.entity.CrmBanner;
import com.itlike.cmsservice.mapper.CrmBannerMapper;
import com.itlike.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author ZYQ
 * @since 2020-10-12
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Cacheable(value = "banner", key = "'bannerindexList'")
    @Override
    public List<CrmBanner> bannerAll() {
        QueryWrapper<CrmBanner> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.last("limit 1");
        List<CrmBanner> crmBanners = baseMapper.selectList(queryWrapper);
        return crmBanners;
    }
}
