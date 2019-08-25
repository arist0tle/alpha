package com.geektcp.alpha.util.queue;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by TangHaiyang on 2019/8/25.
 */
public class SynchronousQueueTest {

    private static class SynchronousQueueProducer implements Runnable {

        private BlockingQueue<String> blockingQueue;
        final Random random = new Random();

        private SynchronousQueueProducer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String data = UUID.randomUUID().toString();
                    System.out.println("Put: " + data);
                    blockingQueue.put(data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private static class SynchronousQueueConsumer implements Runnable {

        private BlockingQueue<String> blockingQueue;

        private SynchronousQueueConsumer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run(){
            while (true) {
                try {
                    String data = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + " take(): " + data);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();

        SynchronousQueueProducer queueProducer = new SynchronousQueueProducer(synchronousQueue);
        new Thread(queueProducer).start();

        SynchronousQueueConsumer queueConsumer1 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer1).start();

        SynchronousQueueConsumer queueConsumer2 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer2).start();

        synchronousQueue.wait();

    }


    /**
     * 在单线程中SynchronousQueue只要put就立即休眠当前线程，只能在另外一个线程中解锁
     */
    private static SynchronousQueue<String> queue = new SynchronousQueue<>();

    @Test
    public void addAndPoll() throws Exception{
        queue.put("aaaa");
        System.out.println("已经陷入休眠状态！");
        String element = queue.take();
        System.out.println(element);
    }


}
