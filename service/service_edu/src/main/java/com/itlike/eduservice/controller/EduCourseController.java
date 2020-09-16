package com.itlike.eduservice.controller;


import com.itlike.eduservice.entity.vo.courseInfoVo;
import com.itlike.eduservice.service.EduCourseService;
import com.itlike.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
   private EduCourseService eduCourseService;
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


}

