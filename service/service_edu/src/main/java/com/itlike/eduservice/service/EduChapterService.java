package com.itlike.eduservice.service;

import com.itlike.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itlike.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ZYQ
 * @since 2020-09-02
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByid(String courseId);

    boolean delechapter(String id);
}
