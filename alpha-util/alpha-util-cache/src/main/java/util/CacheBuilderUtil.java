package util;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by tanghaiyang on 2019/3/8.
 * guava tool class: CacheBuilder
 */
public class CacheBuilderUtil {

    private static RemovalListener<String, Integer> myRemovalListener = new RemovalListener<String, Integer>() {
        @Override
        public void onRemoval(RemovalNotification<String, Integer> notification) {
            String tips = String.format("key=%s,value=%s,reason=%s in myRemovalListener", notification.getKey(), notification.getValue(), notification.getCause());
            System.out.println(tips);
            //when expireAfterAccess to do
            if (notification.getCause().equals(RemovalCause.EXPIRED) && notification.getValue() != null) {
                System.out.printf("Remove %s in cacheConnection", notification.getKey());
            }
        }
    };

    /*
    * expireAfterWrite will clean cache
    * refreshAfterWrite will delay old cache
    * */
    private static LoadingCache<String, Integer> CACHE = CacheBuilder.newBuilder()
            .refreshAfterWrite(3, TimeUnit.SECONDS)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(myRemovalListener)
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
