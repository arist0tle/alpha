package com.geektcp.alpha.util.base.proxy.example.bird;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class BirdLogProxy implements Flyable {
    private Flyable flyable;

    public BirdLogProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        System.out.println("Bird fly start...");

        flyable.fly();

        System.out.println("Bird fly end...");
    }
}
