package com.geektcp.alpha.design.pattern.singleton;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class Singleton {

    private static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
