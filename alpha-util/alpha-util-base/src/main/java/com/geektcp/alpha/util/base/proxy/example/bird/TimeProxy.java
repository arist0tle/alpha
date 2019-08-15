package com.geektcp.alpha.util.base.proxy.example.bird;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class TimeProxy implements Flyable {
    private Flyable flyable;

    public TimeProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        long start = System.currentTimeMillis();

        this.flyable.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly Time =" + (end - start));
    }
}

