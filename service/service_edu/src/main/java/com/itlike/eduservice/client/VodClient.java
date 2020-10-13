package com.itlike.eduservice.client;

import com.itlike.eduservice.client.impl.VodClientImpl;
import com.itlike.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod",fallback = VodClientImpl.class)
@Component
public interface VodClient {
    @DeleteMapping("/eduvod/video/removeAlyVideo/{videoid}")
    public Result removeAlyVideo(@PathVariable(value = "videoid") String videoid);
    @DeleteMapping("/eduvod/video/delete-batch")
    public Result deleteBatch(@RequestParam("videoList") List<String> videoList);
}
