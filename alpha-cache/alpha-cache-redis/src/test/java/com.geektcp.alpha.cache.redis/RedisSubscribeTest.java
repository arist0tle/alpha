package com.geektcp.alpha.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tanghaiyang on 2019/6/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "")
@Slf4j
public class RedisSubscribeTest {

    @Autowired
    private RedisMessageListenerContainer redisListenerContainer;

    @Test
    public void subscribe() throws InterruptedException {
        log.info("start Listener");
        RedisReceiver redisReceiver = new RedisReceiver();
        MessageListenerAdapter adapter =  new MessageListenerAdapter(redisReceiver, "receiveMessage");
        adapter.afterPropertiesSet();
        redisListenerContainer.addMessageListener(adapter, new ChannelTopic("testChannel"));
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }

}
