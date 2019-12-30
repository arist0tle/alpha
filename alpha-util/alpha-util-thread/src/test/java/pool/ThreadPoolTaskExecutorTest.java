package pool;

import com.geektcp.alpha.util.thread.demo.excutor.runnable.RunnableImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTaskExecutorTest {
    private static ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    public static void main(String[] args) {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();
        int taskSize = 10;
        if(args.length == 1) {
            taskSize = Integer.parseInt(args[0]);
        }
        System.out.println("-------- taskSize --------: " + taskSize);

        // 创建一个线程池
//        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        init();

        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Runnable r = new RunnableImpl(i);
            executor.submit(r);
        }

        // 关闭线程池
        executor.shutdown();

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }


    ////////////////////////////////
    private static void init() {

        final int nThreads = Runtime.getRuntime().availableProcessors() + 1;

        //配置核心线程数
        executor.setCorePoolSize(nThreads);
        //配置最大线程数
        executor.setMaxPoolSize(nThreads);
        //配置队列大小
        executor.setQueueCapacity(99999);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("prometheus-service-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
    }
}
