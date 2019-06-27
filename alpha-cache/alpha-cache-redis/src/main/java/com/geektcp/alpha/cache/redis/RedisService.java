package com.geektcp.alpha.cache.redis;

/**
 * Created by tanghaiyang on 2019/6/25.
 */
public interface RedisService {

    boolean hasKey(String key);

    <T> T get(String key);

    <T> boolean put(String key, T value);

    <T> boolean put(String key, T value, int timeout);

    boolean delete(String key);

    boolean publish(String channel, Object message);

}