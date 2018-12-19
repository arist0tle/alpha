package com.haizhi.learn.sector1;

/* Created by Haiyang on 2017/11/4. */

public class MyThread3 extends Thread {
    private volatile boolean flag = true;
    protected void stopTask() {
        flag = false;
    }

    @Override
    public void run() {

        while (flag) {
            // 执行一些不会阻塞的任务 ...
        }
    }


    public static void main(String[] args) {
        Thread t1 = new MyThread3();
        t1.start();
        t1.interrupt();
        System.out.println(t1.getName() +"|" + t1.getState() + " is interrupted.");

    }

}
