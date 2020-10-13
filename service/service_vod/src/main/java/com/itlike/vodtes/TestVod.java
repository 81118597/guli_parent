package com.itlike.vodtes;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.itlike.Vod.Utils.InitObject;

import java.util.List;


public class TestVod {
    public static void main(String[] args) throws ClientException {
        //上传视频
        UploadVideoRequest request = new UploadVideoRequest("LTAI4GA76zMtgTRZWaExJTda","REhBb0pOns8xF4VSJRpftg0oHKOT8H","视频nice","C:/Users/81118/Desktop/6 - What If I Want to Move Faster.mp4");
        request.setPartSize(2*1024*1024L);
        request.setTaskNum(1);

        UploadVideoImpl uploadVideo=new UploadVideoImpl();
        UploadVideoResponse response=uploadVideo.uploadVideo(request);
        System.out.println(response.getVideoId());
    }
    public static void getPlycredentials() throws ClientException {
        DefaultAcsClient initVodClient = InitObject.initVodClient("LTAI4GA76zMtgTRZWaExJTda", "REhBb0pOns8xF4VSJRpftg0oHKOT8H");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse  response = new GetVideoPlayAuthResponse();
        request.setVideoId("7a66e99a653748028235977de10e2411");
        response = initVodClient.getAcsResponse(request);
        //播放凭证
        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
    }
    public static void getPlayUrl() throws ClientException {
        DefaultAcsClient initVodClient = InitObject.initVodClient("LTAI4GA76zMtgTRZWaExJTda", "REhBb0pOns8xF4VSJRpftg0oHKOT8H");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        request.setVideoId("7a66e99a653748028235977de10e2411");
        response=initVodClient.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

}
