package com.itlike.eduservice.service;

import com.itlike.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ZYQ
 * @since 2020-08-12
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> teacherAll();
}
