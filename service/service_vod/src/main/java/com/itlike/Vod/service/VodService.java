package com.itlike.Vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideoAly(MultipartFile file);

    void removeAlyVideo(String videoid);

    void deleteBatch(List<String> videoList);

}
