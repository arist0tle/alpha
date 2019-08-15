package com.geektcp.alpha.util.base.proxy.example.bird;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class Bird2 extends Bird {

    @Override
    public void fly() {
        long start = System.currentTimeMillis();

        super.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}

