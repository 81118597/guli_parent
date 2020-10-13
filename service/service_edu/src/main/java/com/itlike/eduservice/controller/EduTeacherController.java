package com.itlike.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itlike.eduservice.entity.EduTeacher;
import com.itlike.eduservice.entity.vo.TeacherQuery;
import com.itlike.eduservice.service.EduTeacherService;
import com.itlike.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ZYQ
 * @since 2020-08-12
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @ApiOperation(value = "查询")
    @GetMapping("/findAll")
    public Result findAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("list",list);
    }
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result removeTeacher(@ApiParam(name="id",value = "讲师ID",required = true) @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
    @ApiOperation(value = "分页")
    @PostMapping("/pageTeacher/{current}/{limit}")
    public Result pageTeacher(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> Page = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("datetime",end);
        }
        wrapper.orderByDesc("sort");
        eduTeacherService.page(Page,wrapper);
        long total = Page.getTotal();//总记录数
        List<EduTeacher> records = Page.getRecords();//数据list集合
        return Result.ok().data("total",total).data("rows",records);
    }
    @ApiOperation(value = "添加")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
    //根据讲师id进行查询
    @ApiOperation(value = "id查询")
    @GetMapping("/getTeacher/{id}")
    public Result getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher",eduTeacher);
    }

    //讲师修改功能
    @ApiOperation(value = "修改")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        System.out.println(eduTeacher);
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

}

