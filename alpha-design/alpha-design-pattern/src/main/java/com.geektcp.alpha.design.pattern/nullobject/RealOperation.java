package com.geektcp.alpha.design.pattern.nullobject;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class RealOperation extends AbstractOperation {
    @Override
    void request() {
        System.out.println("do something");
    }
}