package com.geektcp.alpha.design.pattern.strategy;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("quack!");
    }
}
