package com.geektcp.alpha.design.pattern.iterator;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public interface Iterator<Item> {

    Item next();

    boolean hasNext();
}
