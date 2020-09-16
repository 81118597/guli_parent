package com.itlike.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itlike.eduservice.entity.EduChapter;
import com.itlike.eduservice.entity.EduVideo;
import com.itlike.eduservice.entity.chapter.ChapterVo;
import com.itlike.eduservice.entity.chapter.VideoVo;
import com.itlike.eduservice.mapper.EduChapterMapper;
import com.itlike.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itlike.eduservice.service.EduCourseService;
import com.itlike.eduservice.service.EduVideoService;
import com.itlike.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ZYQ
 * @since 2020-09-02
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByid(String courseId) {
        //1.根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);
        //2.根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduVideo> list = eduVideoService.list(wrapper1);
        //3.遍历查询章节的list的集合进行封装
        List<ChapterVo> chapterVos=new ArrayList<>();
        for(EduChapter chapter:eduChapters){
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVos.add(chapterVo);
            List<VideoVo> videoVos=new ArrayList<>();
            for (EduVideo eduVideo : list) {
                if(eduVideo.getChapterId().equals(chapter.getId())){
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChapterVos(videoVos);

        }
        return chapterVos;
    }

    @Override
    public boolean  delechapter(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        int count = eduVideoService.count(wrapper);
        if(count>0){
            throw new GuliException(20001,"不能删除");
        }else{
            int i = baseMapper.deleteById(id);
            if(i>0){
                return true;
            }else {
                return false;
            }
        }

    }
}
