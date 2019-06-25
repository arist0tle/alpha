package com.geektcp.alpha.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by tanghaiyang on 2019/6/25.
 */
@Service
@Slf4j
public class RedisReceiver {

    public void receiveMessage(String message) {
        log.info("test receive message!");
        // do something
    }
}
