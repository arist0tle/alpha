package com.geektcp.alpha.spring.querydsl.service.util;

/**
 * Created by tanghaiyang on 2019/1/3.
 */
public class ConditionUtil {
    public static String wrap(String field){
        return  (field == null ? "%%" : "%" + field + "%");
    }
}
