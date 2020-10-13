package com.itlike.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itlike.eduservice.entity.EduTeacher;
import com.itlike.eduservice.mapper.EduTeacherMapper;
import com.itlike.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author ZYQ
 * @since 2020-08-12
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Cacheable(value = "Teacher", key = "'TeacherList'")
    @Override
    public List<EduTeacher> teacherAll() {
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>() ;
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 8");
        List<EduTeacher> eduTeachers = baseMapper.selectList(queryWrapper);
        return eduTeachers;
    }
}
