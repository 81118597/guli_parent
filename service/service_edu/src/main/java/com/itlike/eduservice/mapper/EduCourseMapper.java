package com.itlike.eduservice.mapper;

import com.itlike.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itlike.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author ZYQ
 * @since 2020-09-02
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
     CoursePublishVo getCoursePublish(String id);
}
