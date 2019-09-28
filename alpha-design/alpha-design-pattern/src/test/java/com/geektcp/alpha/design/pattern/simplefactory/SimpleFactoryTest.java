package com.geektcp.alpha.design.pattern.simplefactory;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Product product = simpleFactory.createProduct(1);
        // do something with the product
    }
}
