package com.itlike.eduservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.itlike.eduservice.client.VodClient;
import com.itlike.eduservice.entity.EduChapter;
import com.itlike.eduservice.entity.EduVideo;
import com.itlike.eduservice.entity.chapter.ChapterVo;
import com.itlike.eduservice.service.EduChapterService;
import com.itlike.eduservice.service.EduVideoService;
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
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;
    //课程大纲列表
    @GetMapping("/getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable("courseId") String courseId){
        List<ChapterVo> chapterVos=chapterService.getChapterVideoByid(courseId);
        return Result.ok().data("list",chapterVos);
    }
    @PostMapping("/addchapter")
    public Result chapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return Result.ok();
    }
    @GetMapping("/getchapterByid/{id}")
    public Result getchapterByid(@PathVariable String id){
        EduChapter byId = chapterService.getById(id);
        return Result.ok().data("list",byId);
    }
    @PostMapping("/updatechapter")
    public Result updatechapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return Result.ok();
    }
    @DeleteMapping("/delechapter/{id}")
    public Result delechapter(@PathVariable String id){
        boolean flag = chapterService.delechapter(id);
        if(flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

}

