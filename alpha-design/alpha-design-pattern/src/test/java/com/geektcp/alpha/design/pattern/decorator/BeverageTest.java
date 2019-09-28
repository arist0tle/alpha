package com.geektcp.alpha.design.pattern.decorator;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class BeverageTest {
    public static void main(String[] args) {
        Beverage beverage = new HouseBlend();
        beverage = new Mocha(beverage);
        beverage = new Milk(beverage);
        System.out.println(beverage.cost());
    }
}
