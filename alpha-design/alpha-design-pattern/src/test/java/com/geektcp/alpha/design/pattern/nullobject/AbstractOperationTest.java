package com.geektcp.alpha.design.pattern.nullobject;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class AbstractOperationTest {
    public static void main(String[] args) {
        AbstractOperation abstractOperation = func(-1);
        abstractOperation.request();
    }

    public static AbstractOperation func(int para) {
        if (para < 0) {
            return new NullOperation();
        }
        return new RealOperation();
    }
}
