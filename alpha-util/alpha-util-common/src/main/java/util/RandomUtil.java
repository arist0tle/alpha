package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;

/**
 * Created by tanghaiyang on 2019/2/22.
 */
@Slf4j
public class RandomUtil {

    public static void main(String[] args) {
        RandomUtil o = new RandomUtil();
        log.info("random int : {}", o.generateInt(10));

    }


    public int generateInt(int max){
        final Random random = new Random(System.currentTimeMillis());

        return  random.nextInt(max);
    }



}
