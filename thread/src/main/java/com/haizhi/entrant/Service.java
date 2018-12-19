package com.haizhi.entrant;

/* Created by Haiyang on 2018/2/22. */


import java.util.concurrent.locks.Lock;

public class Service {
    synchronized public void service1(){
        System.out.println("service1");
        service2();
    }


    synchronized public void service2(){
        System.out.println("service2");
        service3();
    }

    synchronized public void service3(){
        System.out.println("service3");
    }



}
