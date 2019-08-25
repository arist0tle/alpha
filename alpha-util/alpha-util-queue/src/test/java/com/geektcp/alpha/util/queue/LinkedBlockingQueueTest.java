package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by TangHaiyang on 2019/8/25.
 */
public class LinkedBlockingQueueTest {


    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @Test
    public void addAndPoll(){
        queue.add("aaaa");
        String element = queue.poll();
        System.out.println(element);
    }
}
