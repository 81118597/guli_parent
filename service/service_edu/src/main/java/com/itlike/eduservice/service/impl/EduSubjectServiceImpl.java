package com.itlike.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itlike.eduservice.entity.EduSubject;
import com.itlike.eduservice.entity.subject.OneSubject;
import com.itlike.eduservice.entity.subject.TwoSubject;
import com.itlike.eduservice.mapper.EduSubjectMapper;
import com.itlike.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ZYQ
 * @since 2020-08-22
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        List<EduSubject> eduSubjects = baseMapper.selectList(wrapper);

        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper.ne("parent_id",0);
        List<EduSubject> eduSubjects1 = baseMapper.selectList(wrapper1);
        List<OneSubject> list=new ArrayList<>();

        for(EduSubject edu:eduSubjects){
            OneSubject o=new OneSubject();
            BeanUtils.copyProperties(edu,o);
//            o.setId(edu.getId());
//            o.setTitle(edu.getTitle());
            list.add(o);
            List<TwoSubject> tow=new ArrayList<>();
            for(EduSubject stu2:eduSubjects1){
                if(stu2.getParentId().equals(edu.getId())){
                    TwoSubject t=new TwoSubject();
                    BeanUtils.copyProperties(stu2,t);
                    tow.add(t);
                }
            }
            o.setChildren(tow);

        }
        return list;
    }
}
