package com.geektcp.alpha.util.base.proxy.example.bird;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class Bird4 implements Flyable {
    private Flyable flyable;

    public Bird4(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        long start = System.currentTimeMillis();

        flyable.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}

