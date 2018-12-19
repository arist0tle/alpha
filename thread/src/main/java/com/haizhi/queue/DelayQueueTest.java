package com.haizhi.queue;

/* Created by Haiyang on 2018/3/16. */


import java.util.Random;
import java.util.concurrent.*;

public class DelayQueueTest {
    static final int STUDENT_SIZE = 1;
    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();

        // 把所有学生看做一个延迟队列
        DelayQueue<StudentQueue> students = new DelayQueue<StudentQueue>();

        for ( int i = 0; i < STUDENT_SIZE; i++) {
            //初始化学生的姓名和做题时间
            StudentQueue s = new StudentQueue( "学生" + (i + 1), 3000 + r.nextInt(10000));
            students.put(s);
        }

        // 构造一个线程池用来让学生们“做作业” StudentQueue开始做题
        ExecutorService exec = Executors.newFixedThreadPool(STUDENT_SIZE);
        while(! students.isEmpty()){
            // 类似linuxC， execute执行一个命令，程序不需要获取结果，submit执行一个命令，返回一个结果
            exec.execute( students.take() );
//            exec.submit(students.take());
        }
        exec.shutdown();
    }
}
