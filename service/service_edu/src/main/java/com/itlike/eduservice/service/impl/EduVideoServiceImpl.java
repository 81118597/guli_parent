package com.itlike.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itlike.eduservice.client.VodClient;
import com.itlike.eduservice.entity.EduVideo;
import com.itlike.eduservice.mapper.EduVideoMapper;
import com.itlike.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author ZYQ
 * @since 2020-09-02
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private VodClient vodClient;

    @Override
    public void  removeByCourseId(String id) {
        QueryWrapper<EduVideo> wrapperVideo= new QueryWrapper<>();
        wrapperVideo.eq("course_id",id);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapperVideo);
        System.out.println("数据1"+eduVideos);
        System.out.println("数据 2"+eduVideos);
        List<String> videoIds=new ArrayList<>();
        for (EduVideo video : eduVideos) {
            String videoSourceId = video.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }
        if(videoIds.size()>0){
            vodClient.deleteBatch(videoIds);
        }
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
