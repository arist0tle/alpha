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
            System.out.println("onRemoval thread id: " + Thread.currentThread().getId());
            //when expireAfterAccess to do
            if (notification.getCause().equals(RemovalCause.EXPIRED) && notification.getValue() != null) {
                System.out.printf("Remove %s in cacheConnection\n", notification.getKey());
            }
        }
    };

    /*
    * expireAfterWrite will clean cache
    * refreshAfterWrite will delay old cache
    * */
    private static LoadingCache<String, Integer> CACHE = CacheBuilder.newBuilder()
//            .refreshAfterWrite(3, TimeUnit.SECONDS)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(myRemovalListener)
            .build(new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String key) throws Exception {
                    return 100;
                }
            });

    /*
    * CacheBuilder 完全没有使用任何线程，它的listener是通过回调函数实现的
    * 下面的例子表明当达到listener的时间即第5s，listener函数依然没有执行，除非执行get方法，
    * 这是先执行钩子函数，然后返回get结果。
    * */
    public static void main(String[] args) throws Exception{
        System.out.println("thread id: " + Thread.currentThread().getId());
        CACHE.put("aaa", 55);

//        System.out.println(CACHE.get("aaa"));
        Thread.sleep(3000);System.out.println("过了3秒");

        System.out.println(CACHE.get("aaa"));

        Thread.sleep(1000); System.out.println("过了4秒");
        Thread.sleep(1000); System.out.println("过了5秒");
        Thread.sleep(1000); System.out.println("过了6秒");
        Thread.sleep(1000); System.out.println("过了7秒");
        Thread.sleep(1000); System.out.println("过了8秒");
//        System.out.println(CACHE.get("aaa"));

        System.out.println("dddddddddddd");
        System.out.println(CACHE.get("aaa"));
    }
}
