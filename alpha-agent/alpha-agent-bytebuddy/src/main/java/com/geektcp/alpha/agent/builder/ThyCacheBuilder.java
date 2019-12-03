package com.geektcp.alpha.agent.builder;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author haiyang.tang on 12.02 002 18:00:19.
 */
public class ThyCacheBuilder {

    private static ConcurrentMap<String, AtomicLong> thyCache = new ConcurrentHashMap<>(10000);

    private ThyCacheBuilder() {
    }


    private static ThyCacheBuilder singleton = null;

    public static ThyCacheBuilder getInstance() {
        if (singleton == null) {
            singleton = new ThyCacheBuilder();
        }
        return singleton;
    }

    public static AtomicLong get(String key) {
        return thyCache.getOrDefault(key, new AtomicLong(0));
    }

    public static void put(String key, AtomicLong value) {
        thyCache.put(key, value);
    }

    public static void clear() {
        thyCache.clear();
    }

    public static AtomicLong incrementAndGet(String key) {
        AtomicLong value = get(key);
        value.incrementAndGet();
        return value;
    }

    public static List<String> listCache() {
        List<String> list = Lists.newArrayList();
        Set<String> keys = thyCache.keySet();
        try {
            for (String key : keys) {
                if (Objects.isNull(thyCache.getOrDefault(key, new AtomicLong(0)))) {
                    continue;
                }
                list.add(key + " " + thyCache.getOrDefault(key, new AtomicLong(0)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
