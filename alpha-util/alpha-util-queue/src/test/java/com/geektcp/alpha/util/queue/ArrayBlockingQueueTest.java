package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by TangHaiyang on 2019/8/25.
 * 规定大小的BlockingQueue,其构造函数必须带一个int参数来指明其大小.
 * 其所含的对象是以FIFO(先入先出)顺序排序的.
 */
public class ArrayBlockingQueueTest {

    private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    @Test
    public void addAndPoll(){
        queue.add("aaaa");
        String element = queue.poll();
        System.out.println(element);
    }


}
