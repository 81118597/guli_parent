package com.itlike.eduservice.service.impl;

import com.itlike.eduservice.entity.EduCourse;
import com.itlike.eduservice.entity.EduCourseDescription;
import com.itlike.eduservice.entity.vo.courseInfoVo;
import com.itlike.eduservice.mapper.EduCourseMapper;
import com.itlike.eduservice.service.EduCourseDescriptionService;
import com.itlike.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itlike.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ZYQ
 * @since 2020-09-02
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(courseInfoVo courseInfoVo) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert<=0){
            throw  new GuliException(20001,"添加课程信息失败");
        }
        String cid=eduCourse.getId();
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setId(cid);
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;
    }

    @Override
    public courseInfoVo getcourseInfo(String id) {
        courseInfoVo v=new courseInfoVo();
        EduCourse eduCourse = baseMapper.selectById(id);
        EduCourseDescription byId = eduCourseDescriptionService.getById(id);
        BeanUtils.copyProperties(eduCourse,v);
        BeanUtils.copyProperties(byId,v);
        return v;
    }

    @Override
    public void updatecourse(courseInfoVo vo) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(vo,eduCourse);
        int row = baseMapper.updateById(eduCourse);
       if(row==0){
           throw new GuliException(20001,"修改课程信息失败");
       }
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        BeanUtils.copyProperties(vo,eduCourseDescription);
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

}
