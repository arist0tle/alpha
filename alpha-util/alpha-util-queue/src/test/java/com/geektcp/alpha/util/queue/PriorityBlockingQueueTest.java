package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by TangHaiyang on 2019/8/25.
 */
public class PriorityBlockingQueueTest {


    private static PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();

    @Test
    public void addAndPoll(){
        queue.add("cccc");
        queue.add("aaaa");
        queue.add("bbbb");
        String element = queue.poll();
        System.out.println(element);
    }
}
