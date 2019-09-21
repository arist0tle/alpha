package com.geektcp.alpha.design.pattern.prototype;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class ConcretePrototypeTest {
    public static void main(String[] args) {
        Prototype prototype = new ConcretePrototype("abc");
        Prototype clone = prototype.myClone();
        System.out.println(clone.toString());
    }
}
