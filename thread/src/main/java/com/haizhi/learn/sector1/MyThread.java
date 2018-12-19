package com.haizhi.learn.sector1;

/* Created by Haiyang on 2017/11/4. */


public class MyThread extends Thread {
//    @Override
//    public void run() {
//        super.run();
//        System.out.println("MyThread");
//    }

    private int i;

    public MyThread(int i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i + "|"
                + Thread.currentThread().getName() + "|"
                + Thread.currentThread().getId()
        );
    }
}