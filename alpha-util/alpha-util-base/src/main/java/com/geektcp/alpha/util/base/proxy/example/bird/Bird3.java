package com.geektcp.alpha.util.base.proxy.example.bird;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class Bird3 implements Flyable {
    private Bird bird;

    public Bird3(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void fly() {
        long start = System.currentTimeMillis();

        bird.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}

