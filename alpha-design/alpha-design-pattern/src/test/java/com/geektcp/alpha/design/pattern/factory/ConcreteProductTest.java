package com.geektcp.alpha.design.pattern.factory;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class ConcreteProductTest {
    public static void main(String[] args) {
        int type = 1;
        Product product;
        if (type == 1) {
            product = new ConcreteProduct1();
        } else if (type == 2) {
            product = new ConcreteProduct2();
        } else {
            product = new ConcreteProduct();
        }
        // do something with the product
    }
}
