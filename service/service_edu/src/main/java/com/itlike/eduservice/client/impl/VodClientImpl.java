package com.itlike.eduservice.client.impl;

import com.itlike.eduservice.client.VodClient;
import com.itlike.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VodClientImpl implements VodClient {
    @Override
    public Result removeAlyVideo(String videoid) {
        return Result.error().message("错误");
    }

    @Override
    public Result deleteBatch(List<String> videoList) {
        return Result.error().message("错误");
    }
}
