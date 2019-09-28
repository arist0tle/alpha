package com.geektcp.alpha.design.pattern.template;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class CaffeineBeverageTest {
    public static void main(String[] args) {
        CaffeineBeverage caffeineBeverage = new Coffee();
        caffeineBeverage.prepareRecipe();
        System.out.println("-----------");
        caffeineBeverage = new Tea();
        caffeineBeverage.prepareRecipe();
    }
}
