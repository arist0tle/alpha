package com.geektcp.alpha.design.pattern.adapter;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class TurkeyAdapterTest {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck duck = new TurkeyAdapter(turkey);
        duck.quack();
    }
}
