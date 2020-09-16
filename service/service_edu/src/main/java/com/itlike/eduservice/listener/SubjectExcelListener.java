package com.itlike.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itlike.eduservice.entity.EduSubject;
import com.itlike.eduservice.entity.excel.entity.SubjectData;
import com.itlike.eduservice.service.EduSubjectService;
import com.itlike.servicebase.exceptionhandler.GlobalExceptionHandler;
import com.itlike.servicebase.exceptionhandler.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new GuliException(20001,"文件数据为空");
        }
        EduSubject eduSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if(eduSubject==null){
            eduSubject=new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
           subjectService.save(eduSubject);
        }
        String pid=eduSubject.getId();
        EduSubject eduSubject1 = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if(eduSubject1==null){
            eduSubject1=new EduSubject();
            eduSubject1.setParentId(pid);
            eduSubject1.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(eduSubject1);
        }
    }
    private EduSubject existOneSubject(EduSubjectService subjectService, String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
