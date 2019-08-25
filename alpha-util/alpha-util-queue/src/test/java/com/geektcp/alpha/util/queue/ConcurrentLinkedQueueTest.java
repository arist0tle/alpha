package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by TangHaiyang on 2019/8/25.
 */
public class ConcurrentLinkedQueueTest {

    private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    @Test
    public void addAndPoll(){
        queue.add("aaaa");
        String element = queue.poll();
        System.out.println(element);
    }

}
