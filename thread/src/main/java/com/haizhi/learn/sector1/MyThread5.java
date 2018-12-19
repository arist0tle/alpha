package com.haizhi.learn.sector1;

/* Created by Haiyang on 2017/11/4. */

public class MyThread5 extends Thread {
    @Override
    public void run() {
        try {
            // 1. isInterrupted()保证，只要中断标记为true就终止线程。
            while (!isInterrupted()) {
                // 执行任务...
                Thread.sleep(11111);
            }
        } catch (InterruptedException ie) {
            // 2. InterruptedException异常保证，当InterruptedException异常产生时，线程被终止。
        }
    }

    public static void main(String[] args) {
        Thread t1 = new MyThread5();
        t1.start();
        t1.interrupt();
        System.out.println(t1.getName() +"|" + t1.getState() + " is interrupted.");

    }

}