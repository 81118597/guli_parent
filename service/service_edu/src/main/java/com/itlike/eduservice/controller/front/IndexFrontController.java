package com.itlike.eduservice.controller.front;

import com.itlike.eduservice.entity.EduCourse;
import com.itlike.eduservice.entity.EduTeacher;
import com.itlike.eduservice.service.EduCourseService;
import com.itlike.eduservice.service.EduTeacherService;
import com.itlike.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/front")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class IndexFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;
    @GetMapping("/TeacherList")
    public Result TeacherList(){
        List<EduTeacher> TeacherList=teacherService.teacherAll();
        return  Result.ok().data("TeacherList",TeacherList);
    }
    @GetMapping("/CourseList")
    public Result EduAll(){
        List<EduCourse> CourseList=courseService.courseAll();
        return  Result.ok().data("CourseList",CourseList);
    }
}
