package com.geektcp.alpha.design.pattern.strategy;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class DuckTest {
    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.setQuackBehavior(new Squeak());
        duck.performQuack();
        duck.setQuackBehavior(new Quack());
        duck.performQuack();
    }
}
