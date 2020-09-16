package com.itlike.eduservice.controller;


import com.itlike.eduservice.entity.EduVideo;
import com.itlike.eduservice.service.EduVideoService;
import com.itlike.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ZYQ
 * @since 2020-09-02
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @PostMapping("/addvideo")
    public Result addvideo(@RequestBody EduVideo video){
        videoService.save(video);
        return Result.ok();
    }
    @DeleteMapping("/delvideo/{id}")
    public Result delvideo(@PathVariable String id){
        videoService.removeById(id);
        return Result.ok();
    }
    @PostMapping("/updatevideo")
    public Result updatevideo(@RequestBody EduVideo video){
        videoService.updateById(video);
        return Result.ok();
    }
    @GetMapping("/getvideo/{id}")
    public Result getvideo(@PathVariable String id){
        EduVideo byId = videoService.getById(id);
        return  Result.ok().data("list",byId);
    }
}

