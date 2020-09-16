package com.itlike.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils implements InitializingBean {
    //读取配置文件内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyid;
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;
    //定义公开静态常量
    public static String END_POIND;

    public static String KEY_ID;

    public static String KEY_SECRET;

    public static String BUCKET_MAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POIND=endpoint;
        KEY_ID=keyid;
        KEY_SECRET=keysecret;
        BUCKET_MAME=bucketname;
    }
}
