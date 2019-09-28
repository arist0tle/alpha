package com.geektcp.alpha.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tanghaiyang on 2019/6/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "")
@Slf4j
public class RedisPublishTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void publish() {
        redisTemplate.convertAndSend("testChannel", "changed");
        log.info("convertAndSend message");
    }
}
