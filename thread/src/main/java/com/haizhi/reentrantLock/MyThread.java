package com.haizhi.reentrantLock;

/* Created by Haiyang on 2018/2/28. */


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {

    @Override
    public  void run() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try{
            System.out.println("1111111");
            for (int i = 0; i < 3; i++) {
                System.out.println("******  " + Thread.currentThread().getName() + " is printing " + i + "  ******");
                // 查询当前线程保持此锁的次数
                int holdCount = lock.getHoldCount();

                // 返回正等待获取此锁的线程估计数
                int queuedLength = lock.getQueueLength();

                // 如果此锁的公平设置为 true，则返回 true
                boolean isFair = lock.isFair();

                System.out.printf("---holdCount: %d;\n---queuedLength:%d;\n---isFair: %s\n\n", holdCount, queuedLength, isFair);
            }
        }finally {
            lock.unlock();
        }


    }


    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        myThread1.start();

        MyThread myThread2 = new MyThread();
        myThread2.start();


    }
}
