package com.geektcp.alpha.design.pattern.visitor;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public interface Element {
    void accept(Visitor visitor);
}
