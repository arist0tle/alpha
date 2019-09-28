package com.geektcp.alpha.design.pattern.abstractfactory;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new ConcreteFactory1();
        AbstractProductA productA = abstractFactory.createProductA();
        AbstractProductB productB = abstractFactory.createProductB();
        // do something with productA and productB
    }
}
