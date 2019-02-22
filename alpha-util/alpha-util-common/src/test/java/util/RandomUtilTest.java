package util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tanghaiyang on 2019/2/22.
 */
@Slf4j
public class RandomUtilTest {
    public static void main(String[] args) {
        RandomUtil o = new RandomUtil();
        log.info("random int: {}", RandomUtil.randomInt(10));
        log.info("random int: {}", RandomUtil.randomInt(10));
        log.info("random int: {}", RandomUtil.randomInt(10));
        log.info("random int: {}", RandomUtil.randomInt(10));

        log.info("random str: {}", RandomUtil.randomString(4));
        log.info("random str: {}", RandomUtil.randomString(4));
        log.info("random str: {}", RandomUtil.randomString(4));
        log.info("random str: {}", RandomUtil.randomString(5));
        log.info("random str: {}", RandomUtil.randomString(64));
        log.info("random str: {}", RandomUtil.randomString(128));

        int[] arr = { 5, 4, 11, 23 };

        log.info("random int: {}", RandomUtil.randomInt(arr));
        log.info("random int: {}", RandomUtil.randomInt(arr));
        log.info("random int: {}", RandomUtil.randomInt(arr));

        String[] arr2 = {"aaa", "bbb","ccc", "ddd"};
        log.info("random arr int: {}", RandomUtil.randomString(arr2));
        log.info("random arr int: {}", RandomUtil.randomString(arr2));
        log.info("random arr int: {}", RandomUtil.randomString(arr2));
        log.info("random arr int: {}", RandomUtil.randomString(arr2));

        List<String> list = new LinkedList<>();
        list.add("ooo");
        list.add("ppp");
        list.add("qqq");
        list.add("sss");
        log.info("random list int: {}", RandomUtil.random(list));
        log.info("random list int: {}", RandomUtil.random(list));
        log.info("random list int: {}", RandomUtil.random(list));
        log.info("random list int: {}", RandomUtil.random(list));
    }

}
