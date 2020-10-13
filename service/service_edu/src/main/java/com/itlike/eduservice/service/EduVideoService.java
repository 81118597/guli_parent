package com.itlike.eduservice.service;

import com.itlike.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author ZYQ
 * @since 2020-09-02
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String id);
}
