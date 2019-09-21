package com.geektcp.alpha.design.pattern.flyweight;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class FlyweightFactoryTest {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.getFlyweight("aa");
        Flyweight flyweight2 = factory.getFlyweight("aa");
        flyweight1.doOperation("x");
        flyweight2.doOperation("y");
    }
}
