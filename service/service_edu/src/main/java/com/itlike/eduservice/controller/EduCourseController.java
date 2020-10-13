package com.itlike.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itlike.eduservice.entity.EduCourse;
import com.itlike.eduservice.entity.vo.CoursePublishVo;
import com.itlike.eduservice.entity.vo.CourseQuery;
import com.itlike.eduservice.entity.vo.courseInfoVo;
import com.itlike.eduservice.service.EduCourseService;
import com.itlike.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ZYQ
 * @since 2020-09-02
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class EduCourseController {
//    Normal
    public static final String CONTROLLER_Normal="Normal";
    @Autowired
   private EduCourseService eduCourseService;

    @PostMapping("/pageCourse/{current}/{limit}")
    public Result pageCourse(@PathVariable Long current, @PathVariable Long limit,@RequestBody(required = false) CourseQuery query){
        Page<EduCourse> page = new Page<>(current, limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(query.getTitle())){
            wrapper.like("title",query.getTitle());
        }
        if(!StringUtils.isEmpty(query.getStatus())){
            wrapper.eq("status",query.getStatus());
        }
        eduCourseService.page(page, wrapper);
        long total = page.getTotal();//总记录数
        List<EduCourse> records = page.getRecords();
        return Result.ok().data("list",records).data("total",total);
    }
    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody courseInfoVo courseInfoVo){
        String id=eduCourseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("CourseId",id);
    }
    @GetMapping("/getcourseInfo/{id}")
    public Result getcourseInfo(@PathVariable String id){
       courseInfoVo  courseInfo=eduCourseService.getcourseInfo(id);
        return Result.ok().data("list",courseInfo);
    }
    @PostMapping("/updatecourse")
    public Result updatecourse(@RequestBody courseInfoVo vo){
        eduCourseService.updatecourse(vo);
        return Result.ok();
    }
    @GetMapping("/CoursePublishInfo/{id}")
    public Result CoursePublishInfo(@PathVariable String id){
        CoursePublishVo coursePublish = eduCourseService.getCoursePublish(id);
        return Result.ok().data("list",coursePublish);
    }
    @PostMapping("CourseUpdate/{id}")
    public Result CourseUpdate(@PathVariable String id){
        EduCourse course=new EduCourse();
        course.setId(id);
        course.setStatus(CONTROLLER_Normal);
        eduCourseService.updateById(course);
        return Result.ok();
    }
    @DeleteMapping("DelCourse/{id}")
    public Result DelCourse(@PathVariable String id){
        eduCourseService.DelCourse(id);
        return Result.ok();
    }


}

