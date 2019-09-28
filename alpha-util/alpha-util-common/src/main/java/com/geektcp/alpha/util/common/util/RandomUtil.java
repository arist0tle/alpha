package com.geektcp.alpha.util.common.util;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Random;

/**
 * Created by tanghaiyang on 2019/2/22.
 */
@Slf4j
public class RandomUtil {


    public static int randomInt(int max){
        final Random random = new Random();

        return  random.nextInt(max);
    }


    public static String randomString(int length){
        final Random random = new Random();
        String raw = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456";
        int rawLength = raw.length();
        String ret = "";
        for(int i=0;i<length;i++) {
            ret += raw.charAt(random.nextInt(rawLength));
        }
        return ret;
    }

    public static int randomInt(int[] arr){
        final Random random = new Random();
        int length = arr.length;
        int seq = random.nextInt(length);
        return  arr[seq];
    }

    public static String randomString(String[] arr){
        final Random random = new Random();
        int length = arr.length;
        int seq = random.nextInt(length);
        return  arr[seq];
    }

    public static <T> T random(List<T> arr){
        final Random random = new Random();
        int length = arr.size();
        int seq = random.nextInt(length);
        return  arr.get(seq);
    }

}
