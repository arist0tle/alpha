package com.geektcp.alpha.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author tanghaiyang on 2019/6/25.
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public <T> T get(String key) {
        Object result;
        try {
            result = redisTemplate.opsForValue().get(key);
            if (null == result) {
                return null;
            }
            return (T) result;
        } catch (Exception e) {
            log.error("Get [Key:{0}] from redis failed!", e, key);
        }
        return null;
    }

    @Override
    public <T> boolean put(String key, T value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("Set [Key:{0}][Value:{1}] into redis failed!", e, key, value);

        }
        return false;
    }

    @Override
    public <T> boolean put(String key, T value, int timeout) {
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("Set [Key:{0}][Value:{1}][TimeOut:{2}] into redis failed!", e, key, value, timeout);
        }
        return false;
    }

    @Override
    public boolean delete(String key) {
        try {
            if (this.hasKey(key)) {
                redisTemplate.delete(key);
            }
            return true;
        } catch (Exception e) {
            log.error("Delete [Key:{0}] from redis failed!", e, key);
        }
        return false;
    }

    @Override
    public boolean publish(String channel, Object message) {
        try {
            redisTemplate.convertAndSend(channel, message);
            return true;
        } catch (Exception e) {
            log.error("Redis publish [channel:{0}][message:{1}] failed!", e, channel, message);
        }
        return false;
    }
}