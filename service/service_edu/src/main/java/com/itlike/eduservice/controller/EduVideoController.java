package com.itlike.eduservice.controller;


import com.itlike.eduservice.client.VodClient;
import com.itlike.eduservice.entity.EduVideo;
import com.itlike.eduservice.service.EduVideoService;
import com.itlike.servicebase.exceptionhandler.GuliException;
import com.itlike.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @Autowired
    private VodClient vodClient;
    @PostMapping("/addvideo")
    public Result addvideo(@RequestBody EduVideo video){
        videoService.save(video);
        return Result.ok();
    }
    @DeleteMapping("/delvideo/{id}")
    public Result delvideo(@PathVariable String id){
        EduVideo byId = videoService.getById(id);
        String VideoSourceId=byId.getVideoSourceId();
        if(!StringUtils.isEmpty(VideoSourceId)){
            Result result = vodClient.removeAlyVideo(VideoSourceId);
            if(result.getCode()==20001){
                throw new GuliException(20001,"熔断器执行");
            }
        }
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

