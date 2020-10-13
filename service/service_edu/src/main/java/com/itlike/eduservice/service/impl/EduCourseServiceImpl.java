package com.itlike.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itlike.eduservice.entity.EduCourse;
import com.itlike.eduservice.entity.EduCourseDescription;
import com.itlike.eduservice.entity.vo.CoursePublishVo;
import com.itlike.eduservice.entity.vo.courseInfoVo;
import com.itlike.eduservice.mapper.EduCourseMapper;
import com.itlike.eduservice.service.EduChapterService;
import com.itlike.eduservice.service.EduCourseDescriptionService;
import com.itlike.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itlike.eduservice.service.EduVideoService;
import com.itlike.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduChapterService chapterService;

    @Override
    public String saveCourseInfo(courseInfoVo courseInfoVo) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        EduCourse eduCourse1 = baseMapper.selectById(eduCourse.getId());
        if(!StringUtils.isEmpty(eduCourse1)){
            baseMapper.updateById(eduCourse);
        }else{
            int insert = baseMapper.insert(eduCourse);
            if(insert<=0){
                throw  new GuliException(20001,"添加课程信息失败");
            }
        }
        String cid=eduCourse.getId();
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setId(cid);
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        EduCourseDescription byId = eduCourseDescriptionService.getById(cid);
        if(!StringUtils.isEmpty(byId)){
            eduCourseDescriptionService.updateById(eduCourseDescription);
        }else{
            eduCourseDescriptionService.save(eduCourseDescription);
        }
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

    @Override
    public CoursePublishVo getCoursePublish(String id) {
        return baseMapper.getCoursePublish(id);
    }

    @Override
    public void DelCourse(String id) {
        //1.根据课程id删除小节
        eduVideoService.removeByCourseId(id);
        //2.根据课程id删除章节
        chapterService.removechapterByCourseIdId(id);
        //3.根据课程id删除描述0
        eduCourseDescriptionService.removeById(id);
        //4.根据课程id删除课程本身
        int row = baseMapper.deleteById(id);
        if(row==0){
            throw new GuliException(20001,"删除失败");
        }
    }
    @Cacheable(value = "Course", key = "'CourseList'")
    @Override
    public List<EduCourse> courseAll() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.last("limit 8");
        List<EduCourse> eduCourses = baseMapper.selectList(wrapper);
        return eduCourses;
    }

}
