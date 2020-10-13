package com.itlike.eduservice.service;

import com.itlike.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itlike.eduservice.entity.vo.CoursePublishVo;
import com.itlike.eduservice.entity.vo.courseInfoVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ZYQ
 * @since 2020-09-02
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(courseInfoVo courseInfoVo);

    courseInfoVo getcourseInfo(String id);

    void updatecourse(courseInfoVo vo);

    CoursePublishVo getCoursePublish(String id);

    void DelCourse(String id);

    List<EduCourse> courseAll();
}
