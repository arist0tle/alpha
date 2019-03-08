package util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * Created by tanghaiyang on 2019/3/8.
 */
public class CacheBuilderUtil {
    private static LoadingCache<String, Integer> CACHE = CacheBuilder.newBuilder()
            .refreshAfterWrite(3, TimeUnit.SECONDS)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build(new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String key) throws Exception {
                    return 100;
                }
            });


    public static void main(String[] args) throws Exception{
        CACHE.put("aaa", 1);

        System.out.println(CACHE.get("aaa"));
        Thread.sleep(4000);

        System.out.println(CACHE.get("aaa"));
    }
}
