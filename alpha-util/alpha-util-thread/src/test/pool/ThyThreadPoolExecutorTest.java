package pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by TangHaiyang on 2019/8/20.
 *
 */
public class ThyThreadPoolExecutorTest {

    private static final int poolSize = 10;

    /**
     * Executors通过包装ThreadPoolExecutor，得到了若干线程池
     *
     */
    @Test
    public void ThreadPoolExecutorTest(){
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(poolSize);
        Executors.newSingleThreadScheduledExecutor();
        Executors.newWorkStealingPool(poolSize);
        Executors.newFixedThreadPool(poolSize);



    }

}
