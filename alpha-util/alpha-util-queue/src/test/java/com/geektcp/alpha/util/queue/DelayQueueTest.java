package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by TangHaiyang on 2019/8/25.
 */
public class DelayQueueTest {

    private static DelayQueue<DelayBean> queue = new DelayQueue<>();

    @Test
    public void addAndPoll(){
        DelayBean delayBean = new DelayBean();
        queue.add(delayBean);
        DelayBean element = queue.poll();
        System.out.println(element);
    }

    private class DelayBean implements Delayed {
        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
