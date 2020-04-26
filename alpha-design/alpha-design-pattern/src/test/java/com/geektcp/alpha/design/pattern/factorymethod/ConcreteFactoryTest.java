package com.geektcp.alpha.design.pattern.factorymethod;

import com.geektcp.alpha.design.pattern.simplefactory.Product;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class ConcreteFactoryTest {
    public static void main(String[] args) {
        ConcreteFactory concreteFactory = new ConcreteFactory();
        Product product = concreteFactory.factoryMethod();
    }
}
