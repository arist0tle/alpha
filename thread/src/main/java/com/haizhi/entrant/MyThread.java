package com.haizhi.entrant;

/* Created by Haiyang on 2018/2/22. */


public class MyThread extends Thread{
    @Override
    public void run(){
        Service service = new Service();
        service.service1();
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
