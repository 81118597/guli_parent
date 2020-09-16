package com.itlike.eduservice.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写的操作
//        String filename="D:\\weite.xls";
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
        //实现excel读的操作
        String filename="D:\\weite.xls";
        EasyExcel.read(filename,DemoData.class,new enerExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            DemoData d=new DemoData();
            d.setSno(i);
            d.setSname("stu"+i);
            list.add(d);
        }
        return list;
    }
}
