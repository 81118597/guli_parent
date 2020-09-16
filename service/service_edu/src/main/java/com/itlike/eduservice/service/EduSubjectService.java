package com.itlike.eduservice.service;

import com.itlike.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itlike.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ZYQ
 * @since 2020-08-22
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}
