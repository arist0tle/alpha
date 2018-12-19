package com.haizhi.queue;

/* Created by Haiyang on 2018/3/16. */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Slf4j
public class StudentQueue implements Runnable,Delayed {
    private String name;  //姓名
    private long costTime;//做试题的时间
    private long finishedTime;//完成时间

    public StudentQueue(String name, int costTime) {
        this. name = name;
        this.costTime = costTime;
        finishedTime = costTime + System.currentTimeMillis();
    }

    @Override
    public void run() {
        log.info("{} 交卷,用时:{} s",name , costTime/1000);
    }

    @Override
    public long getDelay(TimeUnit unit) {
//        Log.logger.info("getDelay: {}", unit);
        return ( finishedTime - System. currentTimeMillis());
    }

    @Override
    public int compareTo(Delayed o) {
        log.info("compareTo: {}",o);
        StudentQueue other = (StudentQueue) o;
        return costTime >= other.costTime?1:-1;
    }

}
