package com.itlike.Vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.itlike.Vod.Utils.ConstantVodUtils;
import com.itlike.Vod.Utils.InitObject;
import com.itlike.Vod.service.VodService;
import com.itlike.servicebase.exceptionhandler.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            String fileName=file.getOriginalFilename();
            String title=fileName.substring(0,fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET,title,fileName,inputStream);

            UploadVideoImpl uploadVideo = new UploadVideoImpl();
            UploadStreamResponse response = uploadVideo.uploadStream(request);

            String videoId=null;
            if(response.isSuccess()){
                videoId=response.getVideoId();
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeAlyVideo(String videoid) {
        DefaultAcsClient defaultAcsClient = InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoid);
        try {
            defaultAcsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new GuliException(20001,"删除视频失败");
        }
    }

    @Override
    public void deleteBatch(List<String> videoList) {
        DefaultAcsClient defaultAcsClient = InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
        DeleteVideoRequest request = new DeleteVideoRequest();
       String videoIds=StringUtils.join(videoList.toArray(),",");
        request.setVideoIds(videoIds);
        try {
            defaultAcsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new GuliException(20001,"删除视频失败");
        }
    }

}
