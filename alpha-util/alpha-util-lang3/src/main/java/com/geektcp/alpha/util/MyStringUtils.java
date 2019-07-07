package com.geektcp.alpha.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by TangHaiyang on 2019/7/7.
 */
@Slf4j
public class MyStringUtils {

    @Test
    public void test(){
        String a = "";
        String b = null;
        String c = " ";
        String d = "abc";
        String e = "llllsdfdf123";
        String[] aa = {"ddd", "ddffff"};
        log.info("StringUtils.isAnyEmpty(a, b, c): {}",StringUtils.isAnyEmpty(a, b, c));
        log.info("StringUtils.isAllBlank(a,c): {}", StringUtils.isAllBlank(a,c));
        log.info("StringUtils.isAllBlank(a,c): {}", StringUtils.isAllBlank(a,c));
        log.info("ClassUtils.getName(this.getClass():{}", ClassUtils.getName(this.getClass()));
        log.info("ClassUtils.getPackageName(this.getClass()):{}", ClassUtils.getPackageName(this.getClass()));
        log.info("ClassUtils.getCanonicalName(this.getClass()):{}", ClassUtils.getCanonicalName(this.getClass()));

        log.info("StringUtils.isAlpha(d): {}", StringUtils.isAlpha(d));
        log.info("StringUtils.isAlpha(e): {}", StringUtils.isAlpha(e));
        log.info("StringUtils.isNumeric(e): {}", StringUtils.isNumeric(e));

    }

    public void say(String word){
        System.out.println(word);
    }
}
