package com.geektcp.alpha.util.base.proxy.example.bird;

import java.util.Random;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("Bird is flying...");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}