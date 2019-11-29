package alpha.geektcp.agent.repository;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author haiyang.tang on 11.27 027 18:27:09.
 */
public class CacheRepository {

    private CacheRepository() {
    }

    private static Cache<String, Object> CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES).build();

    public static void put(String key, Object o) {
        CACHE.put(key, o);
    }

    public static Object get(String key) {
        return CACHE.getIfPresent(key);
    }
}
