package com.itlike.cmsservice.service;

import com.itlike.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author ZYQ
 * @since 2020-10-12
 */
public interface CrmBannerService extends IService<CrmBanner> {
    List<CrmBanner> bannerAll();
}
