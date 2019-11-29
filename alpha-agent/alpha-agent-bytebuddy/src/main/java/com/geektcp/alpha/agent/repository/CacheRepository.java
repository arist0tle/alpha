package com.geektcp.alpha.agent.repository;

import com.google.common.cache.*;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author haiyang.tang on 11.27 027 18:27:09.
 */
public class CacheRepository {

    private CacheRepository() {
    }

    public static Cache<String, AtomicLong> CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES).build();

    public static void put(String key, AtomicLong o) {
        CACHE.put(key, o);
    }

    public static AtomicLong getIfPresent(String key) {
        return CACHE.getIfPresent(key);
    }

    public static AtomicLong get(String key) {
        return CACHE.getIfPresent(key);
    }

    public static List<String> listCache() {
        List<String> list = Lists.newArrayList();
        Set<String> keys = CACHE.asMap().keySet();
        try {
            for (String key : keys) {
                if(Objects.isNull(CACHE.getIfPresent(key))){
                    continue;
                }
                list.add(key + " " + CACHE.getIfPresent(key));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static void clear(){
        CACHE.cleanUp();
    }
}
