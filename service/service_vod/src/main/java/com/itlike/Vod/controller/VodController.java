package com.itlike.Vod.controller;

import com.itlike.Vod.service.VodService;
import com.itlike.utils.Result;
import org.apache.ibatis.annotations.Delete;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin(allowCredentials = "true")//解决跨域问题
public class VodController {
    @Autowired
    private VodService vodService;
    @PostMapping("/uploadAlyiVideo")
    public Result uploadAlyiVideo(MultipartFile file){
        String videoId=vodService.uploadVideoAly (file);
        return Result.ok().data("videoId",videoId);
    }
    @DeleteMapping("/removeAlyVideo/{videoid}")
    public Result removeAlyVideo(@PathVariable String videoid){
        vodService.removeAlyVideo(videoid);
        return Result.ok();
    }
    @DeleteMapping("/delete-batch")
    public Result deleteBatch(@RequestParam("videoList") List<String> videoList){
        vodService.deleteBatch(videoList);
        return Result.ok();
    }
}
