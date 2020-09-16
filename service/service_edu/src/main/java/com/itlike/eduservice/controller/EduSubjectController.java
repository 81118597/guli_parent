package com.itlike.eduservice.controller;


import com.alibaba.excel.EasyExcel;
import com.itlike.eduservice.entity.excel.entity.SubjectData;
import com.itlike.eduservice.entity.subject.OneSubject;
import com.itlike.eduservice.listener.SubjectExcelListener;
import com.itlike.eduservice.service.EduSubjectService;
import com.itlike.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ZYQ
 * @since 2020-08-22
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    @PostMapping("/addSubject")
    public Result addSubject(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            subjectService.saveSubject(file, subjectService);
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }
    @GetMapping("/getAllSubject")
    public Result getAllSubject() {
        List<OneSubject> list=subjectService.getAllOneTwoSubject();
       return Result.ok().data("list",list);
    }


}

