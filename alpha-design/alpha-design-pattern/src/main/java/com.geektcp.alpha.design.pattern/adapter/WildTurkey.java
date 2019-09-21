package com.geektcp.alpha.design.pattern.adapter;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble!");
    }
}
